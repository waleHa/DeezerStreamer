package com.deezer.domain.remotemodel.album


import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("link")
    val link: String? = "",
    @SerializedName("name")
    val name: String? = "",
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
    @SerializedName("radio")
    val radio: Boolean? = false,
    @SerializedName("role")
    val role: String? = "",
    @SerializedName("share")
    val share: String? = "",
    @SerializedName("tracklist")
    val tracklist: String? = "",
    @SerializedName("type")
    val type: String? = ""
)