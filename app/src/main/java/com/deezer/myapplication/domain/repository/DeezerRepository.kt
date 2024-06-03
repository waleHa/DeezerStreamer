package com.deezer.myapplication.domain.repository

import com.deezer.myapplication.data.remote.model.PlaylistList
import com.deezer.myapplication.data.remote.model.TrackList

interface DeezerRepository {
    suspend fun getPlaylist(): PlaylistList
    suspend fun getTracks(playlistId: String): TrackList
}