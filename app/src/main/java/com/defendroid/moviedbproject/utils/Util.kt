package com.defendroid.moviedbproject.utils

import androidx.appcompat.widget.Toolbar
import java.text.SimpleDateFormat
import java.util.*

fun getImageUrl(size: String, path: String): String {
    return "${ApiUrls.IMAGE_BASE_URL}$size/$path"
}

fun getString(data: Any?): String {
    return data?.let {
        data.toString()
    } ?: ""
}

fun getInt(data: CharSequence?): Int? {

    if (data?.isBlank() == true) return 0
    return try {
        data?.toString()?.toInt() ?: 0
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        null
    }
}

fun setAppBarTitle(toolbar: Toolbar?, title: String?, defaultTitle: String) {
    toolbar?.title = title ?: defaultTitle
}

fun isYearValid(year: Int): Boolean {

    if (year == 0) return true

    return try {
        val c: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy")
        val strDate: String = sdf.format(c.time)

        val nextYear = strDate.toInt() + 1
        !(year > nextYear || year < 1700)
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}