package com.deezer.myapplication.presentation.viewmodel.track

sealed class SearchIntent {
    data class SearchTracks(val query: String) : SearchIntent()
}