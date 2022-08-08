package com.gemslight.themoviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.Selection
import androidx.recyclerview.selection.SelectionTracker
import com.gemslight.api_service.use_case.genre.GenreUseCase
import com.gemslight.common.base.AppResponse
import com.gemslight.common.base.BaseViewModel
import com.gemslight.common.entity.genre.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GenreViewModel @Inject constructor(
    application: Application,
    val genreUseCase: GenreUseCase
) : BaseViewModel(application){

    var selectionTracker: SelectionTracker<Long>? = null
    val genreData = MutableLiveData<AppResponse<List<Genre>>>()


    init {
        getAllGenres()
    }


    fun getAllGenres(){
        viewModelScope.launch {
            genreUseCase().collect{
                genreData.postValue(it)
            }
        }
    }
}