package com.example.rappitest.ui.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.rappitest.R
import com.example.rappitest.databinding.ActivityDetailBinding
import com.example.rappitest.domain.model.Movie
import com.example.rappitest.ui.viewmodel.MovieDetailViewModel
import com.example.rappitest.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class DetailActivity : AppCompatActivity() {

    private lateinit var movieSelected: Movie
    var movieId = 0
    private lateinit var bindingDetailActivity: ActivityDetailBinding

    private val movieDetailModel: MovieDetailViewModel by viewModels()
    private val movieViewModel: MovieViewModel by viewModels()

    var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetailActivity = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetailActivity.root)

        movieId = intent.getIntExtra("id_movie",0)

        movieDetailModel.GetMovieById(movieId)

        movieDetailModel.movieByIdModel.observe(this, Observer {
            movieSelected = it
            isFavorite = movieSelected.favorite
            setView()
        })

    }

    private fun setView() {
        bindImage(bindingDetailActivity.imgPoster, movieSelected.poster)
        bindingDetailActivity.txtMovieOverview.text = movieSelected.movie_overview
        bindingDetailActivity.txtReleaseDate.text = movieSelected.release_date
        bindingDetailActivity.txtBudget.text = movieSelected.budget

        if (!movieSelected.trailer){
            bindingDetailActivity.txtVideo.text = "no video"
        }else{
            bindingDetailActivity.txtVideo.text = "video, but no link"
        }

        validateFavorite(isFavorite)

        bindingDetailActivity.imgFavorite.setOnClickListener {
            if (isFavorite){
                bindingDetailActivity.imgFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_non_favorite
                    )
                )
                isFavorite=false
            }else{
                bindingDetailActivity.imgFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_favorite
                    )
                )
                isFavorite=true
            }
            movieDetailModel.UpdateFavoriteMovieById(movieId, isFavorite)
        }
    }

    private fun validateFavorite(favoriteSelected: Boolean) {
        if (favoriteSelected){
            bindingDetailActivity.imgFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_favorite
                )
            )
        }else{
            bindingDetailActivity.imgFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_non_favorite
                )
            )
        }
    }

    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri =
                imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
        }
    }
}