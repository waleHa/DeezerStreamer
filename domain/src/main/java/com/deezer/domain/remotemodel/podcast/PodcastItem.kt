package com.deezer.domain.remotemodel.podcast


import com.google.gson.annotations.SerializedName

data class PodcastItem(
    @SerializedName("available")
    val available: Boolean? = false,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("fans")
    val fans: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("link")
    val link: String? = "",
    @SerializedName("picture")
    val picture: String? = "",
    @SerializedName("picture_big")
    val pictureBig: String? = "",
    @SerializedName("picture_medium")
    val pictureMedium: String? = "",
    @SerializedName("picture_small")
    val pictureSmall: String? = "",
    @SerializedName("picture_xl")
    val pictureXl: String? = "",
    @SerializedName("share")
    val share: String? = "",
    @SerializedName("time_add")
    val timeAdd: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = ""
)