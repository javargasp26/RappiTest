package com.example.rappitest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rappitest.domain.GetMovieByIdUseCase
import com.example.rappitest.domain.UpdateFavoriteMovieByIdUseCase
import com.example.rappitest.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val updateFavoriteMovieByIdUseCase: UpdateFavoriteMovieByIdUseCase,
) : ViewModel() {

    val movieByIdModel = MutableLiveData<Movie>()
    val updateMovieByIdModel = MutableLiveData<Movie>()

    val isLoadingDetailView = MutableLiveData<Boolean>()

    fun GetMovieById(id:Int){
        viewModelScope.launch {
            isLoadingDetailView.postValue(true)
            val result = getMovieByIdUseCase(id)

            movieByIdModel.postValue(result)
            isLoadingDetailView.postValue(false)
        }
    }

    fun UpdateFavoriteMovieById(id:Int, favorite:Boolean){
        viewModelScope.launch {
            isLoadingDetailView.postValue(true)
            val result = updateFavoriteMovieByIdUseCase(id, favorite)

            updateMovieByIdModel.postValue(result)
            isLoadingDetailView.postValue(false)
        }
    }
}