package com.deezer.myapplication.presentation.viewmodel.artist

sealed class ArtistDetailIntent {
    data class LoadArtist(val artistId: String) : ArtistDetailIntent()
}