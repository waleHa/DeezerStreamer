package com.deezer.myapplication.data.remote.model


import com.google.gson.annotations.SerializedName

data class PlaylistList(
    @SerializedName("data")
    val playlistItems: List<PlaylistItem?>? = listOf(),
    @SerializedName("total")
    val total: Int? = 0
)