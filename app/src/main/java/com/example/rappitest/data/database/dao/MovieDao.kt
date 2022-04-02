package com.example.rappitest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rappitest.data.database.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table ORDER BY vote_average DESC")
    suspend fun getAllMovies():List<MovieEntity>

    @Query("SELECT * FROM movie_table where id_movie like :id")
    fun getMovieById(id: Int): MovieEntity

    @Query("UPDATE movie_table SET favorite=:favorite where id_movie like :id")
    fun updateFavoriteMovieById(id: Int, favorite:Boolean)

    @Query("SELECT * FROM movie_table where favorite= 1 ORDER BY vote_average DESC ")
    suspend fun getFavoriteMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies:List<MovieEntity>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllQuotes()
}