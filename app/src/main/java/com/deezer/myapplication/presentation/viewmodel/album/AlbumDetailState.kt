package com.deezer.myapplication.presentation.viewmodel.album

import com.deezer.domain.remotemodel.album.AlbumList

sealed class AlbumDetailState {
    object Loading : AlbumDetailState()
    data class Success(val albumList: AlbumList) : AlbumDetailState()
    data class Error(val message: String) : AlbumDetailState()
}