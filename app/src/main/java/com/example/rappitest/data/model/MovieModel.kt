package com.example.rappitest.data.model

import com.google.gson.annotations.SerializedName


data class MovieModel(
    @SerializedName("id") val id_movie:Int =0,
    @SerializedName("title") val name:String ="",
    @SerializedName("poster_path") val poster:String="",
    @SerializedName("vote_average") val vote_average:String="",
    @SerializedName("overview") val movie_overview:String="",
    @SerializedName("release_date") val release_date:String="",
    @SerializedName("budget") val budget:String="",
    @SerializedName("video") val trailer:Boolean=false,
    @SerializedName("favorite") val favorite:Boolean=false,
    @SerializedName("vote_count") val vote_count:Int=0
)