package com.deezer.myapplication.presentation.intent

sealed class PlaylistIntent {
    data object LoadPlaylists : PlaylistIntent()
}