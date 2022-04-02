package com.example.rappitest.ui.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rappitest.R
import com.example.rappitest.databinding.ActivityMainBinding
import com.example.rappitest.domain.model.Movie
import com.example.rappitest.ui.view.detail.DetailActivity
import com.example.rappitest.ui.view.main.adapter.MovieListAdapter
import com.example.rappitest.ui.view.main.adapter.MovieListHolder
import com.example.rappitest.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint

class MainActivity : AppCompatActivity(), MovieListHolder.ViewHolderListener {

    private lateinit var mainActivityBinding: ActivityMainBinding

    private val movieViewModel: MovieViewModel by viewModels()

    private val movieListAdapter = MovieListAdapter(this)

    var movieList = mutableListOf<Movie>()
    var movieUnderTwoList = mutableListOf<Movie>()
    var movieFavoriteList = mutableListOf<Movie>()

    var showFavorite = false

    private lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        context = this
        movieViewModel.onCreate()

        movieViewModel.isLoading.observe(this, Observer {
            mainActivityBinding.loading.isVisible = it
        })

        movieViewModel.moviesModel.observe(this, Observer {
            movieList = it.toMutableList()
            setView(movieList)

        })

        movieViewModel.moviesFavoriteModel.observe(this, Observer {
            movieFavoriteList.clear()
            movieFavoriteList = it.toMutableList()
            movieListAdapter.setMovieList(movieFavoriteList)
            mainActivityBinding.btnFavorite.text = getString(R.string.All)
            showFavorite=true
        })
    }

    private fun setView(movieList: List<Movie>?) {
        if (movieList.isNullOrEmpty()){
            mainActivityBinding.txtNoMovie.visibility = View.VISIBLE
            mainActivityBinding.rcvMovieList.visibility = View.GONE
            mainActivityBinding.lytBtn.visibility = View.GONE
        }else{
            mainActivityBinding.lytBtn.visibility = View.VISIBLE
            mainActivityBinding.rcvMovieList.layoutManager = LinearLayoutManager(this)
            mainActivityBinding.rcvMovieList.adapter = movieListAdapter
            movieListAdapter.setMovieList(movieList)
            movieUnderTwoList = movieList.filter { it.vote_count >2000 }.toMutableList()
            setOnClickListener()
        }
    }

    private fun setOnClickListener() {

        mainActivityBinding.btnFavorite.setOnClickListener {
            if (showFavorite){
                movieListAdapter.setMovieList(movieList)
                mainActivityBinding.btnFavorite.text = getString(R.string.favorite)
                showFavorite=false
            }else{
                movieViewModel.onUpdateFavorite()
            }
        }

        mainActivityBinding.edtSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.toString()!="" && s.toString().length>2){
                    searchByName(s.toString())
                }else{
                    Toast.makeText(context, "Escribe en el buscador lo que quieres encontrar. Escribe al menos tres caracteres", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun searchByName(query:String){

        val newMovieList: MutableList<Movie> = movieList.filter { it.name == query }.toMutableList()

        if (newMovieList.isNotEmpty()){
            movieListAdapter.setMovieList(newMovieList)
        }else{
            movieListAdapter.setMovieList(movieList)
        }

    }


    override fun onClick(position: Int) {
        val movieSelected = movieList[position]
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id_movie", movieSelected.id_movie)
        startActivity(intent)
    }
}