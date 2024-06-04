package com.deezer.domain.repository

import com.deezer.domain.remotemodel.artist.Artist
import com.deezer.domain.remotemodel.playlist.PlaylistList
import com.deezer.domain.remotemodel.track.SearchList
import com.deezer.domain.remotemodel.track.TrackList
import com.deezer.domain.remotemodel.album.AlbumList
import com.deezer.domain.remotemodel.artist.ArtistTrackList
import com.deezer.domain.remotemodel.podcast.PodcastList
import com.deezer.domain.remotemodel.radio.RadioItem
import com.deezer.domain.remotemodel.radio.RadioResponse


interface DeezerRepository {
    suspend fun getPlaylist(): PlaylistList
    suspend fun getPodcast(): PodcastList
    suspend fun getTracks(playlistId: String): TrackList
    suspend fun searchTracks(query: String): SearchList
    suspend fun getAlbumById(albumId: String): AlbumList
    suspend fun getArtistsById(artistId: String): Artist

    suspend fun getArtistTracks(url: String): ArtistTrackList

    suspend fun getRadio(): RadioResponse
}