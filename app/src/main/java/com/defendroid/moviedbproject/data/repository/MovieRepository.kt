package com.defendroid.moviedbproject.data.repository

import ListResponse
import com.defendroid.moviedbproject.data.api.ApiHelper
import io.reactivex.Single

class MovieRepository(private val apiHelper: ApiHelper) {

    fun getNowPlaying(): Single<ListResponse> {
        return apiHelper.getNowPlaying()
    }

    fun searchMovies(
        searchQuery: String,
        includeAdult: Boolean = false,
        year: Int?
    ): Single<ListResponse> {
        return apiHelper.searchMovies(searchQuery, includeAdult, year)
    }
}