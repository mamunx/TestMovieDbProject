package com.defendroid.moviedbproject.ui.main.adapter

import Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.defendroid.moviedbproject.R
import com.defendroid.moviedbproject.utils.AppConstants
import com.defendroid.moviedbproject.utils.Util
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    private val movies: ArrayList<Movie>
) : RecyclerView.Adapter<MovieAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.tv_movie_name.text = movie.original_title
            itemView.tv_movie_year.text = movie.release_date
            Glide.with(itemView.iv_avatar.context)
                .load(Util.getImageUrl(AppConstants.LOGO_SIZE_MEDIUM, movie.poster_path))
                .into(itemView.iv_avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent,
                false
            )
        )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(movies[position])

    fun addData(list: List<Movie>) {
        movies.addAll(list)
    }

}