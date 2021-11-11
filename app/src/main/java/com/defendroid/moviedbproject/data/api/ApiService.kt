package com.defendroid.moviedbproject.data.api

import ListResponse
import io.reactivex.Single

interface ApiService {
    fun getNowPlaying(): Single<ListResponse>
    fun searchMovies(searchQuery: String, includeAdult: Boolean = false, year: Int?): Single<ListResponse>
}