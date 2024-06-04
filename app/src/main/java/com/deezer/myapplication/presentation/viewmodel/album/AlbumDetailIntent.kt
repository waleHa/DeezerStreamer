package com.deezer.myapplication.presentation.viewmodel.album

sealed class AlbumDetailIntent {
    data class LoadAlbum(val albumId: String) : AlbumDetailIntent()
}