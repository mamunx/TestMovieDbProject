package com.defendroid.moviedbproject.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.defendroid.moviedbproject.data.api.ApiHelper
import com.defendroid.moviedbproject.data.repository.MovieRepository
import com.defendroid.moviedbproject.ui.main.viewmodel.MovieViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}