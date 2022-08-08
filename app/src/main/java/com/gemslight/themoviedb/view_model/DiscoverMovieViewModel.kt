package com.gemslight.themoviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gemslight.api_service.use_case.discover_movie.DiscoverMoviePagingUseCase
import com.gemslight.api_service.use_case.genre.GenreUseCase
import com.gemslight.common.base.BaseViewModel
import com.gemslight.common.entity.discover_movie.DiscoverMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DiscoverMovieViewModel @Inject constructor(
    application: Application,
    val discoverMoviePagingUseCase: DiscoverMoviePagingUseCase
) : BaseViewModel(application) {
    val discoverMovieDataState = MutableLiveData<PagingData<DiscoverMovie>>()


    fun getDiscoverMovie(genres: String) {
        if (discoverMovieDataState.value == null) {
            viewModelScope.launch {
                discoverMoviePagingUseCase.invoke(genres).cachedIn(this).collect {
                    discoverMovieDataState.postValue(it)
                }
            }

        } else {
            discoverMovieDataState.postValue(discoverMovieDataState.value)
        }
    }
}