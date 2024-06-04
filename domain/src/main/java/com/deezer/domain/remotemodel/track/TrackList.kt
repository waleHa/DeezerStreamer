package com.deezer.domain.remotemodel.track


import com.google.gson.annotations.SerializedName

data class TrackList(
    @SerializedName("checksum")
    val checksum: String? = "",
    @SerializedName("data")
    val trackItems: List<TrackItem>? = listOf(),
    @SerializedName("total")
    val total: Int? = 0
)