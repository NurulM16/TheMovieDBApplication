package com.gemslight.api_service.use_case.movie_detail

import com.gemslight.api_service.service.movie_detail.MovieDetailService
import com.gemslight.common.base.AppResponse
import com.gemslight.common.entity.movie.movie_detail.MovieDetailResponse
import kotlinx.coroutines.flow.flow

class MovieDetailUseCase (private val movieDetailService: MovieDetailService) {
    suspend operator fun invoke(movieId: Int) = flow {
        emit(AppResponse.loading())
        try {
            val data = movieDetailService.getMovieDetails(movieId)
            if (data.isSuccessful) {
                data.body()?.let {
                    emit(AppResponse.success(it))
                } ?: kotlin.run {
                    emit(
                        AppResponse.errorBackend<MovieDetailResponse>(
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