package com.gemslight.api_service.service.movie_detail

import com.gemslight.api_service.Constants.API_KEY
import com.gemslight.common.entity.movie.movie_review.MovieReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieReviewService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ) : Response<MovieReviewResponse>
}