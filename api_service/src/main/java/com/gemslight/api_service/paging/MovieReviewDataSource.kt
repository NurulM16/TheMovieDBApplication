package com.gemslight.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gemslight.api_service.service.movie_detail.MovieReviewService
import com.gemslight.common.entity.movie.movie_review.Review

class MovieReviewDataSource(
    private val movieReviewService: MovieReviewService,
    private val movieId: Int
) : PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else page - 1
        return try {
            val data = movieReviewService.getMovieReviews(
                movieId, page
            )
            if (data.isSuccessful) {
                data.body()?.let {
                    val nextPage = if (it.results.isEmpty()) null else page + 1
                    LoadResult.Page(it.results, prevPage, nextPage)
                } ?: run {
                    LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else {
                LoadResult.Error(Exception("Error Backend: ${data.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        fun createPager(
            pageSize: Int,
            movieReviewService: MovieReviewService,
            movieId: Int
        ) = Pager<Int, Review>(
            PagingConfig(pageSize), null
        )
        {
            MovieReviewDataSource(movieReviewService, movieId)
        }
    }
}