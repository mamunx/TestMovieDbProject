package com.defendroid.moviedbproject.data.api

import ListResponse
import Movie
import io.reactivex.Single

interface ApiService {
    fun getNowPlaying(): Single<ListResponse>
    fun getPopular(): Single<List<Movie>>
    fun getUpcoming(): Single<List<Movie>>
}