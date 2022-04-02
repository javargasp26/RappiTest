package com.example.rappitest.ui.view.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rappitest.R
import com.example.rappitest.ui.view.main.adapter.MovieListHolder
import com.example.rappitest.domain.model.Movie

class MovieListAdapter(
    private val viewHolderListener: MovieListHolder.ViewHolderListener
) : RecyclerView.Adapter<MovieListHolder>() {

    private val movieList = mutableListOf<Movie>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_movie, parent, false)
        this.context = parent.context
        return MovieListHolder(view, viewHolderListener)
    }

    override fun getItemCount() = movieList.size


    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.bind(context, movieList[position])
    }

    fun setMovieList(movieList: List<Movie>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }


    fun isEmpty(): Boolean {
        return movieList.isEmpty()
    }

}