package com.deezer.data.repository

import com.deezer.data.remote.network.DeezerService
import com.deezer.domain.remotemodel.artist.ArtistTrackList
import com.deezer.domain.remotemodel.podcast.PodcastList
import com.deezer.domain.remotemodel.radio.RadioItem
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

class DeezerRepositoryImp @Inject constructor(private val deezerService: DeezerService) :
    DeezerRepository {
    override suspend fun getPlaylist() = deezerService.getPlaylist()
    override suspend fun getPodcast() = deezerService.getPodcast()

    override suspend fun getTracks(playlistId: String) = deezerService.getPlaylistTracks(playlistId)
    override suspend fun searchTracks(query: String) =
        deezerService.searchTracks(query)

    override suspend fun getAlbumById(albumId: String) = deezerService.getAlbumById(albumId)
    override suspend fun getArtistsById(artistId: String) = deezerService.getArtistsById(artistId)
    override suspend fun getArtistTracks(artistId: String): ArtistTrackList = deezerService.getArtistTracks(artistId)

    override suspend fun getRadio() = deezerService.getRadio()
}



