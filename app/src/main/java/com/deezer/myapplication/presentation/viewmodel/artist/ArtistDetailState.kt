package com.deezer.myapplication.presentation.viewmodel.artist

import com.deezer.domain.remotemodel.artist.Artist

sealed class ArtistDetailState {
    object Loading : ArtistDetailState()
    data class Success(val artist: Artist) : ArtistDetailState()
    data class Error(val message: String) : ArtistDetailState()
}