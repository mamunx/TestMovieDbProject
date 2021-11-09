package com.defendroid.moviedbproject.data.api

import ListResponse
import Movie
import com.defendroid.moviedbproject.utils.ApiUrls
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getNowPlaying(): Single<ListResponse> {
        return Rx2AndroidNetworking.get(ApiUrls.NOW_PLAYING_URL)
            .build()
            .getObjectSingle(ListResponse::class.java)
    }

    override fun getPopular(): Single<List<Movie>> {
        return Rx2AndroidNetworking.get(ApiUrls.POPULAR_URL)
            .build()
            .getObjectListSingle(Movie::class.java)
    }

    override fun getUpcoming(): Single<List<Movie>> {
        return Rx2AndroidNetworking.get(ApiUrls.UPCOMING_URL)
            .build()
            .getObjectListSingle(Movie::class.java)
    }

}