package com.deezer.data.remote.network

import com.deezer.core.Constant
import com.deezer.domain.remotemodel.artist.Artist
import com.deezer.domain.remotemodel.playlist.PlaylistList
import com.deezer.domain.remotemodel.radio.RadioItem
import com.deezer.domain.remotemodel.track.SearchList
import com.deezer.domain.remotemodel.track.TrackList
import com.deezer.domain.remotemodel.album.AlbumList
import com.deezer.domain.remotemodel.artist.ArtistTrackList
import com.deezer.domain.remotemodel.podcast.PodcastList
import com.deezer.domain.remotemodel.radio.RadioResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerService {
    @GET("user/{userId}/playlists")
    suspend fun getPlaylist(@Path("userId") id: String = "6100338481"): PlaylistList

    @GET("user/{userId}/podcasts")
    suspend fun getPodcast(@Path("userId") id: String = "6100338481"): PodcastList

    @GET("playlist/{playlistId}/tracks")
    suspend fun getPlaylistTracks(@Path("playlistId") id: String): TrackList

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): SearchList

    @GET("album/{albumId}")
    suspend fun getAlbumById(@Path("albumId") id: String = Constant.GENRE_DEFAULT): AlbumList

    @GET("artist/{artistId}")
    suspend fun getArtistsById(@Path("artistId") id: String = Constant.ARTIST_DEFAULT): Artist

    @GET("artist/{artistId}/top?limit=50")
    suspend fun getArtistTracks(@Path("artistId") id: String = Constant.ARTIST_DEFAULT): ArtistTrackList


    @GET("radio")
    suspend fun getRadio(): RadioResponse


}
