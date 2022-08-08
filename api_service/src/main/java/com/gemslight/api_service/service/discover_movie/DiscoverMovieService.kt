package com.gemslight.api_service.service.discover_movie

import com.gemslight.api_service.Constants.API_KEY
import com.gemslight.common.entity.discover_movie.DiscoverMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverMovieService {
    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("with_genres") genres: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ) : Response<DiscoverMovieResponse>
}