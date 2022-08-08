package com.gemslight.themoviedb.module

import com.gemslight.api_service.service.discover_movie.DiscoverMovieService
import com.gemslight.api_service.service.genre.GenreService
import com.gemslight.api_service.service.movie_detail.MovieDetailService
import com.gemslight.api_service.service.movie_detail.MovieReviewService
import com.gemslight.api_service.service.movie_detail.MovieVideoService
import com.gemslight.api_service.use_case.discover_movie.DiscoverMoviePagingUseCase
import com.gemslight.api_service.use_case.genre.GenreUseCase
import com.gemslight.api_service.use_case.movie_detail.MovieDetailUseCase
import com.gemslight.api_service.use_case.movie_detail.MovieReviewPagingUseCase
import com.gemslight.api_service.use_case.movie_detail.MovieVideoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideGenreUseCase(genreService: GenreService) = GenreUseCase(genreService)

    @Provides
    fun provideDiscoverMovieUseCase(discoverMovieService: DiscoverMovieService) =
        DiscoverMoviePagingUseCase(discoverMovieService)

    @Provides
    fun provideMovieDetailUseCase(movieDetailService: MovieDetailService) =
        MovieDetailUseCase(movieDetailService)

    @Provides
    fun provideMovieReviewPagingUseCase(movieReviewService: MovieReviewService) =
        MovieReviewPagingUseCase(movieReviewService)

    @Provides
    fun provideMovieVideoUseCase(movieVideoService: MovieVideoService) =
        MovieVideoUseCase(movieVideoService)
}