package com.example.rappitest.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rappitest.domain.model.Movie

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "id_movie") val id_movie: Int = 0,
    @ColumnInfo(name= "name") val name:String,
    @ColumnInfo(name= "poster") val poster:String,
    @ColumnInfo(name= "vote_average") val vote_average:String,
    @ColumnInfo(name= "movie_overview") val movie_overview:String,
    @ColumnInfo(name= "release_date") val release_date:String,
    @ColumnInfo(name= "budget") val budget:String,
    @ColumnInfo(name= "trailer") val trailer:Boolean,
    @ColumnInfo(name= "favorite") val favorite:Boolean,
    @ColumnInfo(name= "vote_count") val vote_count:Int

)


fun Movie.toDatabase() = MovieEntity(
    id_movie = id_movie,
    name = name,
    poster = poster,
    vote_average = vote_average,
    movie_overview = movie_overview,
    release_date = release_date,
    budget = budget,
    trailer = trailer,
    favorite = favorite,
    vote_count = vote_count)