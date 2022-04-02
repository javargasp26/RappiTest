package com.example.rappitest.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponseModel(
    @SerializedName("results") val results:List<MovieModel>
)