package com.defendroid.moviedbproject.utils

import com.defendroid.moviedbproject.utils.AppConstants.API_KEY
import com.defendroid.moviedbproject.utils.AppConstants.MOVIE
import com.defendroid.moviedbproject.utils.AppConstants.SEARCH

object ApiUrls {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

    const val NOW_PLAYING_URL = "$BASE_URL$MOVIE/now_playing?api_key=$API_KEY"
    const val SEARCH_URL = "$BASE_URL$SEARCH/$MOVIE?api_key=$API_KEY"
}