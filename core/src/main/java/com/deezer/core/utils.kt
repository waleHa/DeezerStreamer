package com.deezer.core


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toReadableDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return inputFormat.parse(this)?.let { outputFormat.format(it) } ?: this
}

fun Int.toReadableDate(): String {
    val date = Date(this.toLong() * 1000)
    val format = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return format.format(date)
}