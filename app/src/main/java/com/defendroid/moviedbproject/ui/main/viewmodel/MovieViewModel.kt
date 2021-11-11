package com.defendroid.moviedbproject.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.defendroid.moviedbproject.data.model.Movie
import com.defendroid.moviedbproject.data.repository.MovieRepository
import com.defendroid.moviedbproject.utils.Resource
import com.defendroid.moviedbproject.utils.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val nowPlayingMovies = MutableLiveData<Resource<List<Movie>>>()
    private val filteredMovies = MutableLiveData<Resource<List<Movie>>>()
    private val compositeDisposable = CompositeDisposable()

    val screenState = MutableLiveData<ScreenState>()

    init {
        fetchNowPlayingMovies()
        screenState.value = ScreenState.NOW_SHOWING
    }

    private fun fetchNowPlayingMovies() {
        nowPlayingMovies.postValue(Resource.loading(null))
        compositeDisposable.add(
            movieRepository.getNowPlaying()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    listResponse.results?.let {
                        nowPlayingMovies.postValue(Resource.success(it))
                    }
                }, {
                    nowPlayingMovies.postValue(
                        Resource.error(
                            it.localizedMessage ?: "Something Went Wrong", null
                        )
                    )
                })
        )
    }

    fun searchMovies(searchQuery: String, includeAdult: Boolean = false, year: Int?) {
        filteredMovies.postValue(Resource.loading(null))
        compositeDisposable.add(
            movieRepository.searchMovies(searchQuery, includeAdult, year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    listResponse.results?.let {
                        filteredMovies.postValue(Resource.success(it))
                    }
                }, {
                    filteredMovies.postValue(
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

    fun getFilteredMovies(): LiveData<Resource<List<Movie>>> {
        return filteredMovies
    }
}