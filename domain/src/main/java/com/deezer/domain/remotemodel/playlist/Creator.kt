package com.deezer.domain.remotemodel.playlist


import com.google.gson.annotations.SerializedName

data class Creator(
    @SerializedName("id")
    val id: Long? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("tracklist")
    val tracklist: String? = "",
    @SerializedName("type")
    val type: String? = ""
)