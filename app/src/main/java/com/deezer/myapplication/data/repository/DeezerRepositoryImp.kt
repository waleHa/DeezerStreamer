package com.deezer.myapplication.data.repository

import com.deezer.myapplication.data.remote.model.TrackList
import com.deezer.myapplication.data.remote.network.DeezerService
import com.deezer.myapplication.domain.repository.DeezerRepository
import javax.inject.Inject

class DeezerRepositoryImp @Inject constructor(private val deezerService: DeezerService) :
    DeezerRepository {
    override suspend fun getPlaylist() = deezerService.getPlaylist()
    override suspend fun getTracks(playlistId: String) = deezerService.getPlaylistTracks(playlistId)
}
