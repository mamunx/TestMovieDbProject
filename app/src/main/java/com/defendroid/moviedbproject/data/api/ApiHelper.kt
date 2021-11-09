package com.defendroid.moviedbproject.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getNowPlaying() = apiService.getNowPlaying()
    fun getPopular() = apiService.getPopular()
    fun getUpcoming() = apiService.getUpcoming()
}