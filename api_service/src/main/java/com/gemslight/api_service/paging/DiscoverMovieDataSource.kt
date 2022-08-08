package com.gemslight.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gemslight.api_service.service.discover_movie.DiscoverMovieService
import com.gemslight.common.entity.discover_movie.DiscoverMovie

class DiscoverMovieDataSource(
    private val discoverMovieService: DiscoverMovieService,
    private val genres: String
) : PagingSource<Int, DiscoverMovie>() {

    override fun getRefreshKey(state: PagingState<Int, DiscoverMovie>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovie> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else page - 1
        return try {
            val data = discoverMovieService.getMovieByGenre(
                genres, page
            )
            if (data.isSuccessful) {
                data.body()?.let {
                    val nextPage = if (it.discoverMovies.isEmpty()) null else page + 1
                    LoadResult.Page(it.discoverMovies, prevPage, nextPage)
                } ?: run {
                    LoadResult.Error(Exception("Error Backend: ${data.code()}"))
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
            discoverMovieService: DiscoverMovieService,
            genres: String
        ) = Pager<Int, DiscoverMovie>(
            PagingConfig(pageSize), null
        )
        {
            DiscoverMovieDataSource(discoverMovieService, genres)
        }
    }
}