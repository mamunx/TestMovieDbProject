package com.defendroid.moviedbproject.ui.main.view

import Movie
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.defendroid.moviedbproject.R
import com.defendroid.moviedbproject.data.api.ApiHelper
import com.defendroid.moviedbproject.data.api.ApiServiceImpl
import com.defendroid.moviedbproject.ui.base.ViewModelFactory
import com.defendroid.moviedbproject.ui.main.adapter.MovieAdapter
import com.defendroid.moviedbproject.ui.main.viewmodel.MovieViewModel
import com.defendroid.moviedbproject.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = MovieAdapter(arrayListOf())
        /*recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )*/
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        movieViewModel.getNowPlayingMovies().observe(this,
            {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let { users -> renderList(users) }
                        search_view.visibility = View.VISIBLE
                        recyclerView.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        search_view.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        //Handle Error
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    private fun renderList(users: List<Movie>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        movieViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MovieViewModel::class.java)
    }
}