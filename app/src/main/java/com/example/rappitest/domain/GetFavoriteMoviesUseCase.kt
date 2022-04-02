package com.example.rappitest.domain

import com.example.rappitest.data.MovieRepository
import com.example.rappitest.domain.model.Movie
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke():List<Movie>{

        var movies = repository.getFavoriteMoviesFromDatabase()

        return movies
    }
}