package com.deezer.data.repository

import com.deezer.data.remote.network.DeezerService
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

class DeezerRepositoryImp @Inject constructor(private val deezerService: DeezerService) :
    DeezerRepository {
    override suspend fun getPlaylist() = deezerService.getPlaylist()
    override suspend fun getTracks(playlistId: String) = deezerService.getPlaylistTracks(playlistId)
}
