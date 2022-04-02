package com.example.rappitest.ui.view.main.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rappitest.R
import com.example.rappitest.domain.model.Movie

class MovieListHolder (itemView: View, viewHolderListener: ViewHolderListener) :
    RecyclerView.ViewHolder(itemView) {

    val txtName: TextView = itemView.findViewById(R.id.txtName)
    val imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
    val txtRate: TextView = itemView.findViewById(R.id.txtRate)
    val imgGoDetail: ImageView = itemView.findViewById(R.id.imgGoDetail)
    val holder: ViewHolderListener = viewHolderListener

    fun bind(context: Context, movie: Movie) {
        txtName.text = movie.name
        txtRate.text = movie.vote_average
        bindImage(imgPoster, movie.poster)
        imgGoDetail.setOnClickListener {
            holder.onClick(
                layoutPosition
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

    interface ViewHolderListener {
        fun onClick(
            position: Int
        )
    }
}