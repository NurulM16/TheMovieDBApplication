package com.gemslight.api_service.use_case.genre

import com.gemslight.api_service.service.genre.GenreService
import com.gemslight.common.base.AppResponse
import com.gemslight.common.entity.genre.Genre
import kotlinx.coroutines.flow.flow

class GenreUseCase(private val genreService: GenreService) {
    suspend operator fun invoke() = flow<AppResponse<List<Genre>>> {
        emit(AppResponse.loading())
        try {
            val data = genreService.getGenre()
            if (data.isSuccessful) {
                data.body()?.let {
                    emit(AppResponse.success(it.genres))
                } ?: kotlin.run {
                    emit(
                        AppResponse.errorBackend(
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