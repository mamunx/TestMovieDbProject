package com.defendroid.moviedbproject.utils

import com.defendroid.moviedbproject.utils.AppConstants.API_KEY

object ApiUrls {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

    const val MIDDLEWARE_MOVIE = "movie"

    const val NOW_PLAYING_URL = "$BASE_URL$MIDDLEWARE_MOVIE/now_playing?api_key=$API_KEY"
    const val POPULAR_URL = "$BASE_URL$MIDDLEWARE_MOVIE/popular?api_key=$API_KEY"
    const val UPCOMING_URL = "$BASE_URL$MIDDLEWARE_MOVIE/upcoming?api_key=$API_KEY"
}