package com.deezer.myapplication.presentation.viewmodel.artist

import com.deezer.domain.remotemodel.artist.Artist
import com.deezer.domain.remotemodel.track.TrackItem

sealed class ArtistDetailState {
    object Loading : ArtistDetailState()
    data class Success(val artist: Artist, val tracks: List<TrackItem>) : ArtistDetailState()
    data class Error(val message: String) : ArtistDetailState()
}