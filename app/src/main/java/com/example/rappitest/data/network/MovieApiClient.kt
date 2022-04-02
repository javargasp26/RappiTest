package com.example.rappitest.data.network

import com.example.rappitest.data.model.MovieResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiClient{
    @GET("/3/movie/top_rated?api_key=675c998103e550f86d328ad324667b87")
    suspend fun getAllMovies(): Response<MovieResponseModel>
}