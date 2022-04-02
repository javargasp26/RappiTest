package com.example.rappitest.domain

import com.example.rappitest.data.MovieRepository
import com.example.rappitest.data.database.entities.toDatabase
import com.example.rappitest.domain.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke():List<Movie>{

        var movies = repository.getAllMoviesFromDatabase()

        if (movies.isNullOrEmpty()){
            movies = repository.getAllMoviesFromApi()
            if(movies.isNotEmpty()){
                repository.clearMovies()
                repository.insertMovies(movies.map { it.toDatabase() })
            }
        }

        return movies
    }
}