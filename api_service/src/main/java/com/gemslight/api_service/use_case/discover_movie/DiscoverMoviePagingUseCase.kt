package com.gemslight.api_service.use_case.discover_movie

import com.gemslight.api_service.paging.DiscoverMovieDataSource
import com.gemslight.api_service.service.discover_movie.DiscoverMovieService

class DiscoverMoviePagingUseCase(
    val discoverMovieService: DiscoverMovieService
) {
    operator fun invoke(genres: String) =
        DiscoverMovieDataSource.createPager(10, discoverMovieService, genres).flow
}