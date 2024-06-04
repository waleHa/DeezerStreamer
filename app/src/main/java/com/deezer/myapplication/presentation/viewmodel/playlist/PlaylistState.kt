package com.deezer.myapplication.presentation.viewmodel.playlist

import com.deezer.domain.remotemodel.playlist.PlaylistItem

sealed class PlaylistState {
    data object Loading : PlaylistState()
    data class Success(val playlists: List<PlaylistItem>) : PlaylistState()
    data class Error(val message: String) : PlaylistState()
}

