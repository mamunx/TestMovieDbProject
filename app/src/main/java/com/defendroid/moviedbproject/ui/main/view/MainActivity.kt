package com.defendroid.moviedbproject.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.defendroid.moviedbproject.R
import com.defendroid.moviedbproject.data.api.ApiHelper
import com.defendroid.moviedbproject.data.api.ApiServiceImpl
import com.defendroid.moviedbproject.data.model.Movie
import com.defendroid.moviedbproject.ui.base.ItemClickListener
import com.defendroid.moviedbproject.ui.base.ViewModelFactory
import com.defendroid.moviedbproject.ui.main.adapter.MovieAdapter
import com.defendroid.moviedbproject.ui.main.viewmodel.MovieViewModel
import com.defendroid.moviedbproject.utils.AppConstants.KEY_SELECTED_MOVIE
import com.defendroid.moviedbproject.utils.ScreenState
import com.defendroid.moviedbproject.utils.Status
import com.defendroid.moviedbproject.utils.setAppBarTitle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {

        setSupportActionBar(toolbar)

        recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = MovieAdapter(arrayListOf(), this)
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {

        movieViewModel.screenState.observe(this, {
            when(it){
                ScreenState.NOW_SHOWING -> {
                    setAppBarTitle(
                        toolbar,
                        getString(R.string.now_playing),
                        getString(R.string.app_name)
                    )
                }
                ScreenState.SEARCH -> {
                    setAppBarTitle(
                        toolbar,
                        getString(R.string.search_result),
                        getString(R.string.app_name)
                    )
                }
            }
        })

        movieViewModel.getNowPlayingMovies().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { movies ->
                        renderList(movies)
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        movieViewModel.getFilteredMovies().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { movies ->
                        renderList(movies)
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(movies: List<Movie>) {
        adapter.addData(movies)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        movieViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MovieViewModel::class.java)
    }

    override fun onItemClicked(item: Any?) {
        if (item != null && item is Movie) {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(KEY_SELECTED_MOVIE, item)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_searach -> {
                //todo open dialog
                movieViewModel.screenState.value = ScreenState.SEARCH
                movieViewModel.searchMovies("Eternals", true, 2021)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (movieViewModel.screenState.value == ScreenState.SEARCH)
            movieViewModel.screenState.value = ScreenState.NOW_SHOWING
        else
            super.onBackPressed()
    }
}