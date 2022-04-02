package com.example.rappitest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rappitest.data.database.dao.MovieDao
import com.example.rappitest.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}