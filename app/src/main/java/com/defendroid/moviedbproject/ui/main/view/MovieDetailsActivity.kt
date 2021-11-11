package com.defendroid.moviedbproject.ui.main.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.bumptech.glide.Glide
import com.defendroid.moviedbproject.R
import com.defendroid.moviedbproject.data.model.Movie
import com.defendroid.moviedbproject.utils.AppConstants
import com.defendroid.moviedbproject.utils.AppConstants.KEY_SELECTED_MOVIE
import com.defendroid.moviedbproject.utils.getImageUrl
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie: Movie? = intent.extras?.getParcelable(KEY_SELECTED_MOVIE)

        movie?.let {

            val imagePath: String = movie.backdrop_path ?: (movie.poster_path ?: "")

            Glide.with(iv_poster.context)
                .load(getImageUrl(AppConstants.BACKDROP_SIZE_LARGE, imagePath))
                .error(R.drawable.ic_error)
                .into(iv_poster)

            tv_movie_name.text = it.original_title
            tv_release_date.text = it.release_date
            tv_vote_count.text = it.vote_count.toString()
            tv_rating.text = it.vote_average.toString()
            tv_description.text = it.overview
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}