package com.deezer.myapplication.presentation.viewmodel.playlist

sealed class PlaylistIntent {
    data object LoadPlaylists : PlaylistIntent()
}