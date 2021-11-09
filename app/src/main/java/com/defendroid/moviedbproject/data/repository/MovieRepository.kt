package com.defendroid.moviedbproject.data.repository

import ListResponse
import Movie
import com.defendroid.moviedbproject.data.api.ApiHelper
import io.reactivex.Single

class MovieRepository(private val apiHelper: ApiHelper) {

    fun getNowPlaying(): Single<ListResponse> {
        return apiHelper.getNowPlaying()
    }

    fun getPopular(): Single<List<Movie>> {
        return apiHelper.getPopular()
    }

    fun getUpcoming(): Single<List<Movie>> {
        return apiHelper.getUpcoming()
    }
}