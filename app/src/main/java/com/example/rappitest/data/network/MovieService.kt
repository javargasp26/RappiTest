package com.example.rappitest.data.network

import android.util.Log
import com.example.rappitest.data.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val api: MovieApiClient) {

    suspend fun getMovies(): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllMovies()
            var list = mutableListOf<MovieModel>()
            if (response.body()!!.results.isNotEmpty()){
                response.body()!!.results.forEach {
                    list.add(
                        MovieModel(
                            it.id_movie,
                            it.name,
                            "https://image.tmdb.org/t/p/original"+it.poster,
                            it.vote_average,
                            it.movie_overview,
                            it.release_date,
                            getBudget(it.budget),
                            it.trailer,
                            it.favorite,
                            it.vote_count
                        )
                    )
                }
            }
            Log.e("here","here")
            list ?: emptyList()
        }
    }

    private fun getBudget(budget: String): String {
        return if (budget.isEmpty()) {
            "no budget found"
        } else {
            "$ $budget"
        }
    }

}