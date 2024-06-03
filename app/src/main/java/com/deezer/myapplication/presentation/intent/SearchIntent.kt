package com.deezer.myapplication.presentation.intent

sealed class SearchIntent {
    data class SearchTracks(val query: String) : SearchIntent()
}