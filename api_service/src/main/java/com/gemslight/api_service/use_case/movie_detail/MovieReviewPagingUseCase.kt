package com.gemslight.api_service.use_case.movie_detail

import com.gemslight.api_service.paging.MovieReviewDataSource
import com.gemslight.api_service.service.movie_detail.MovieReviewService

class MovieReviewPagingUseCase(
    private val movieReviewService: MovieReviewService
) {
    operator fun invoke(movieId: Int) =
        MovieReviewDataSource.createPager(10, movieReviewService, movieId).flow
}