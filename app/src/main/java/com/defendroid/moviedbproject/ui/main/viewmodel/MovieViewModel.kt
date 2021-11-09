package com.defendroid.moviedbproject.ui.main.viewmodel

import Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.defendroid.moviedbproject.data.repository.MovieRepository
import com.defendroid.moviedbproject.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val nowPlayingMovies = MutableLiveData<Resource<List<Movie>>>()
    private val compositeDisposable = CompositeDisposable()

    var selectedMovieLiveData = MutableLiveData<Movie>()

    init {
        fetchNowPlayingMovies()
    }

    private fun fetchNowPlayingMovies() {
        nowPlayingMovies.postValue(Resource.loading(null))
        compositeDisposable.add(
            movieRepository.getNowPlaying()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    nowPlayingMovies.postValue(Resource.success(listResponse.results))
                }, {
                    nowPlayingMovies.postValue(
                        Resource.error(
                            it.localizedMessage ?: "Something Went Wrong", null
                        )
                    )
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>> {
        return nowPlayingMovies
    }
}