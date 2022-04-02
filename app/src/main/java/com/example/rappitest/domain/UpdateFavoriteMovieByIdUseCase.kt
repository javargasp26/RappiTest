package com.example.rappitest.domain

import com.example.rappitest.data.MovieRepository
import com.example.rappitest.domain.model.Movie
import javax.inject.Inject


class UpdateFavoriteMovieByIdUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movie_id: Int, favorite:Boolean): Movie {

        return repository.updateFavoriteMovieByIdFromDatabase(movie_id, favorite)

    }
}