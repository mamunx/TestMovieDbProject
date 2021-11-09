package com.defendroid.moviedbproject.data.api

import Movie
import io.reactivex.Single

interface ApiService {
    fun getNowPlaying(): Single<List<Movie>>
    fun getPopular(): Single<List<Movie>>
    fun getUpcoming(): Single<List<Movie>>
}