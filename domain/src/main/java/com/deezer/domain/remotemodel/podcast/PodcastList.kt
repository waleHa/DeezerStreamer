package com.deezer.domain.remotemodel.podcast


import com.google.gson.annotations.SerializedName

data class PodcastList(
    @SerializedName("checksum")
    val checksum: String? = "",
    @SerializedName("data")
    val `data`: List<PodcastItem?>? = listOf(),
    @SerializedName("total")
    val total: Int? = 0
)