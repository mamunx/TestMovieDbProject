package com.defendroid.moviedbproject.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getNowPlaying() = apiService.getNowPlaying()
    fun searchMovies(searchQuery: String, includeAdult: Boolean = false, year: Int?) =
        apiService.searchMovies(searchQuery, includeAdult, year)
}