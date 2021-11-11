package com.defendroid.moviedbproject.utils

import androidx.appcompat.widget.Toolbar

fun getImageUrl(size: String, path: String): String {
    return "${ApiUrls.IMAGE_BASE_URL}$size/$path"
}

fun getString(data: Any?): String {
    return data?.let {
        data.toString()
    } ?: ""
}

fun setAppBarTitle(toolbar: Toolbar?, title: String?, defaultTitle: String) {
    toolbar?.title = title ?: defaultTitle
}