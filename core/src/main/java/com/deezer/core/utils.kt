package com.deezer.core

import java.text.SimpleDateFormat
import java.util.*

fun String.toReadableDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return inputFormat.parse(this)?.let { outputFormat.format(it) } ?: this
}
