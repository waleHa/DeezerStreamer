package com.deezer.domain.remotemodel.radio


import com.google.gson.annotations.SerializedName


data class RadioResponse(
    @SerializedName("data")
    val data: List<RadioItem>
)

data class RadioItem(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("md5_image")
    val md5Image: String? = "",
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
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("tracklist")
    val tracklist: String? = "",
    @SerializedName("type")
    val type: String? = ""
)