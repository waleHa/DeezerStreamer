package com.deezer.myapplication.data.remote.network

import com.deezer.myapplication.data.remote.model.PlaylistList
import com.deezer.myapplication.data.remote.model.TrackList
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerService {
    @GET("user/{userId}/playlists")
    suspend fun getPlaylist(@Path("userId") id:String="6100338481"): PlaylistList

    @GET("playlist/{playlistId}/tracks")
    suspend fun getPlaylistTracks(@Path("playlistId") id:String): TrackList
}
