package com.defendroid.moviedbproject.utils

object Util {

    fun getImageUrl(size: String, path: String): String {
        return "${ApiUrls.IMAGE_BASE_URL}$size/$path"
    }
}