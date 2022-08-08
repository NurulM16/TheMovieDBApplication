package com.gemslight.api_service.service.genre

import com.gemslight.api_service.Constants.API_KEY
import com.gemslight.common.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey : String = API_KEY
    ) : Response<GenreResponse>
}