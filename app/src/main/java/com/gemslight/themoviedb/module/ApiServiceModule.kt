package com.gemslight.themoviedb.module

import android.content.Context
import com.gemslight.api_service.RetrofitClient
import com.gemslight.api_service.service.discover_movie.DiscoverMovieService
import com.gemslight.api_service.service.genre.GenreService
import com.gemslight.api_service.service.movie_detail.MovieDetailService
import com.gemslight.api_service.service.movie_detail.MovieReviewService
import com.gemslight.api_service.service.movie_detail.MovieVideoService
import com.gemslight.themoviedb.fragment.movie_detail_fragment.MovieDetailFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit) = retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideDiscoverService(retrofit: Retrofit) = retrofit.create(DiscoverMovieService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailService(retrofit: Retrofit) = retrofit.create(MovieDetailService::class.java)

    @Provides
    @Singleton
    fun provideMovieReviewService(retrofit: Retrofit) = retrofit.create(MovieReviewService::class.java)

    @Provides
    @Singleton
    fun provideMovieVideoService(retrofit: Retrofit) = retrofit.create(MovieVideoService::class.java)
}