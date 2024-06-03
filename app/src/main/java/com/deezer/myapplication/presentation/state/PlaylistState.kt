package com.deezer.myapplication.presentation.state

import com.deezer.myapplication.data.remote.model.PlaylistItem

sealed class PlaylistState {
    data object Loading : PlaylistState()
    data class Success(val playlists: List<PlaylistItem>) : PlaylistState()
    data class Error(val message: String) : PlaylistState()
}

