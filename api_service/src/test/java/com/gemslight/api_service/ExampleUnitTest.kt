package com.gemslight.api_service

import com.gemslight.api_service.service.movie_detail.MovieDetailService
import com.gemslight.api_service.service.movie_detail.MovieReviewService
import com.gemslight.api_service.service.movie_detail.MovieVideoService
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test() {
        runBlocking {
            val serviceResponse = RetrofitClient.client.create(MovieVideoService::class.java)
                .getMovieVideos(movieId = 762975)
            println(serviceResponse)
        }
    }
}