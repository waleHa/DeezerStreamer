package com.deezer.myapplication.presentation.intent

sealed class TrackIntent {
    data class LoadTracks(val playlistId: String) : TrackIntent()
}