package com.gemslight.api_service.use_case.movie_detail

import com.gemslight.api_service.service.movie_detail.MovieVideoService
import com.gemslight.common.base.AppResponse
import com.gemslight.common.entity.movie.movie_video.MovieVideoResponse
import kotlinx.coroutines.flow.flow

class MovieVideoUseCase (private val movieVideoService: MovieVideoService) {
    suspend operator fun invoke(movieId: Int) = flow {
        emit(AppResponse.loading())
        try {
            val data = movieVideoService.getMovieVideos(movieId)
            if (data.isSuccessful) {
                data.body()?.let {
                    emit(AppResponse.success(it))
                } ?: kotlin.run {
                    emit(
                        AppResponse.errorBackend<MovieVideoResponse>(
                            data.code(),
                            data.errorBody()
                        )
                    )
                }
            } else {
                emit(AppResponse.errorBackend(data.code(), data.errorBody()))
            }

        } catch (e: Exception) {
            emit(AppResponse.errorSystem(e))
        }
    }
}