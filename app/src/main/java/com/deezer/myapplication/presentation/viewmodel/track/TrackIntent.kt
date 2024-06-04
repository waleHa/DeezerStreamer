package com.deezer.myapplication.presentation.viewmodel.track

sealed class TrackIntent {
    data class LoadTracks(val playlistId: String) : TrackIntent()
}