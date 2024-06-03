package com.deezer.domain.repository

import com.deezer.domain.remotemodel.PlaylistList
import com.deezer.domain.remotemodel.TrackList


interface DeezerRepository {
    suspend fun getPlaylist(): PlaylistList
    suspend fun getTracks(playlistId: String): TrackList
}