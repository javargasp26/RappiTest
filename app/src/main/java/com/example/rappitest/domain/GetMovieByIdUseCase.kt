package com.example.rappitest.domain

import com.example.rappitest.data.MovieRepository
import com.example.rappitest.domain.model.Movie
import javax.inject.Inject


class GetMovieByIdUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movie_id: Int): Movie {

        return repository.getMovieByIdFromDatabase(movie_id)

    }
}