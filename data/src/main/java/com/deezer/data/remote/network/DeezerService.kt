package com.deezer.data.remote.network

import com.deezer.domain.remotemodel.PlaylistList
import com.deezer.domain.remotemodel.TrackList
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerService {
    @GET("user/{userId}/playlists")
    suspend fun getPlaylist(@Path("userId") id:String="6100338481"): PlaylistList

    @GET("playlist/{playlistId}/tracks")
    suspend fun getPlaylistTracks(@Path("playlistId") id:String): TrackList
}
