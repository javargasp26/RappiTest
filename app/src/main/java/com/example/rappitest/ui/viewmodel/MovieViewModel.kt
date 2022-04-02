package com.example.rappitest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rappitest.domain.GetFavoriteMoviesUseCase
import com.example.rappitest.domain.GetMoviesUseCase
import com.example.rappitest.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,

    ) : ViewModel() {

    val moviesModel = MutableLiveData<List<Movie>>()
    val moviesFavoriteModel = MutableLiveData<List<Movie>>()

    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMoviesUseCase()

            if (!result.isNullOrEmpty()) {
                moviesModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun onUpdateFavorite() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFavoriteMoviesUseCase()

            if (!result.isNullOrEmpty()) {
                moviesFavoriteModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}