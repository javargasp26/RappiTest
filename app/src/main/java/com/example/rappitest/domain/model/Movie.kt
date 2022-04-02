package com.example.rappitest.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.example.rappitest.data.database.entities.MovieEntity
import com.example.rappitest.data.model.MovieModel

@Parcelize
data class Movie (
    val id_movie:Int,
    val name:String,
    val poster:String,
    val vote_average:String,
    val movie_overview:String,
    val release_date:String,
    val budget:String,
    val trailer:Boolean,
    val favorite:Boolean,
    val vote_count:Int): Parcelable

fun MovieModel.toDomain() = Movie(id_movie, name, poster,vote_average,movie_overview,release_date,budget,trailer,favorite,vote_count)
fun MovieEntity.toDomain() = Movie(id_movie,name, poster,vote_average,movie_overview,release_date,budget,trailer,favorite,vote_count)