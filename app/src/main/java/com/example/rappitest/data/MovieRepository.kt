package com.example.rappitest.data

import com.example.rappitest.data.database.dao.MovieDao
import com.example.rappitest.data.database.entities.MovieEntity
import com.example.rappitest.data.model.MovieModel
import com.example.rappitest.data.network.MovieService
import com.example.rappitest.domain.model.Movie
import com.example.rappitest.domain.model.toDomain
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val movieDao: MovieDao
) {

    suspend fun getAllMoviesFromApi(): List<Movie> {
        val response: List<MovieModel> = api.getMovies()
        return response.map { it.toDomain() }
    }

    suspend fun getAllMoviesFromDatabase():List<Movie>{
        val response: List<MovieEntity> = movieDao.getAllMovies()
        return response.map { it.toDomain() }
    }

    suspend fun getMovieByIdFromDatabase(id:Int): Movie {
        val response: MovieEntity = movieDao.getMovieById(id)
        return response.toDomain()
    }

    suspend fun updateFavoriteMovieByIdFromDatabase(id:Int, favorite:Boolean): Movie {
        movieDao.updateFavoriteMovieById(id, favorite)
        val response: MovieEntity = movieDao.getMovieById(id)
        return response.toDomain()
    }

    suspend fun getFavoriteMoviesFromDatabase():List<Movie>{
        val response: List<MovieEntity> = movieDao.getFavoriteMovies()
        return response.map { it.toDomain() }
    }

    suspend fun insertMovies(quotes:List<MovieEntity>){
        movieDao.insertAll(quotes)
    }

    suspend fun clearMovies(){
        movieDao.deleteAllQuotes()
    }
}