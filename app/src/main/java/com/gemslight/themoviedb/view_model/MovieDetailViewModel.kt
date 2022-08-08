package com.gemslight.themoviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gemslight.api_service.use_case.movie_detail.MovieDetailUseCase
import com.gemslight.api_service.use_case.movie_detail.MovieReviewPagingUseCase
import com.gemslight.api_service.use_case.movie_detail.MovieVideoUseCase
import com.gemslight.common.base.AppResponse
import com.gemslight.common.base.BaseViewModel
import com.gemslight.common.entity.movie.movie_detail.MovieDetailResponse
import com.gemslight.common.entity.movie.movie_review.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    application: Application,
    val movieDetailUseCase: MovieDetailUseCase,
    val movieReviewPagingUseCase: MovieReviewPagingUseCase,
    val movieVideoUseCase: MovieVideoUseCase
) : BaseViewModel(application) {
    val movieDetailLiveDataState = MutableLiveData<AppResponse<MovieDetailResponse>>()
    val movieReviewPagingDataState = MutableLiveData<PagingData<Review>>()
    val movieVideoLiveDataState = MutableLiveData<String?>()


    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            movieDetailUseCase(movieId).collect {
                movieDetailLiveDataState.postValue(it)
            }
        }
        viewModelScope.launch {
            movieReviewPagingUseCase(movieId).collect {
                movieReviewPagingDataState.postValue(it)
            }
        }
        viewModelScope.launch {
            movieVideoUseCase(movieId).collect {
                it.data?.videos?.get(0)?.key?.let { it1 ->
                    movieVideoLiveDataState.postValue(it1)
                }
            }
        }
    }
}