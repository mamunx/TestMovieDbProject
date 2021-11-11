package com.defendroid.moviedbproject.data.api

import ListResponse
import com.defendroid.moviedbproject.utils.ApiUrls
import com.defendroid.moviedbproject.utils.AppConstants.INCLUDE_ADULT
import com.defendroid.moviedbproject.utils.AppConstants.QUERY
import com.defendroid.moviedbproject.utils.AppConstants.YEAR
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getNowPlaying(): Single<ListResponse> {
        return Rx2AndroidNetworking.get(ApiUrls.NOW_PLAYING_URL)
            .build()
            .getObjectSingle(ListResponse::class.java)
    }

    override fun searchMovies(
        searchQuery: String,
        includeAdult: Boolean,
        year: Int?
    ): Single<ListResponse> {
        val url = StringBuilder()
        url.append(ApiUrls.SEARCH_URL)

        if (searchQuery.isNotBlank())
            url.append("&$QUERY=$searchQuery")

        url.append("&$INCLUDE_ADULT=$includeAdult")

        year?.let {
            url.append("&$YEAR=$it")
        }

        return Rx2AndroidNetworking.get(url.toString())
            .build()
            .getObjectSingle(ListResponse::class.java)
    }


}