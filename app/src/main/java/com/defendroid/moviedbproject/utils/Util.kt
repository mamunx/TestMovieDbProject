package com.defendroid.moviedbproject.utils

fun getImageUrl(size: String, path: String): String {
    return "${ApiUrls.IMAGE_BASE_URL}$size/$path"
}

fun getString(data: Any?): String {
    return data?.let {
        data.toString()
    } ?: ""
}