package com.deezer.domain.remotemodel.artist


import com.deezer.domain.remotemodel.track.TrackItem
import com.google.gson.annotations.SerializedName

data class ArtistTrackList(
    @SerializedName("data")
    val trackItems: List<TrackItem>? = listOf(),
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("total")
    val total: Int? = 0
)