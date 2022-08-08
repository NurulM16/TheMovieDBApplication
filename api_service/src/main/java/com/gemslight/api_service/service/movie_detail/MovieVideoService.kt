package com.gemslight.api_service.service.movie_detail

import com.gemslight.api_service.Constants.API_KEY
import com.gemslight.common.entity.movie.movie_video.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieVideoService {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ) : Response<MovieVideoResponse>
}