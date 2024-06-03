package com.deezer.myapplication.data.remote.model


import com.google.gson.annotations.SerializedName

data class PlaylistItem(
    @SerializedName("checksum")
    val checksum: String? = "",
    @SerializedName("collaborative")
    val collaborative: Boolean? = false,
    @SerializedName("creation_date")
    val creationDate: String? = "",
    @SerializedName("creator")
    val creator: Creator? = Creator(),
    @SerializedName("duration")
    val duration: Int? = 0,
    @SerializedName("fans")
    val fans: Int? = 0,
    @SerializedName("id")
    val id: Long? = 0,
    @SerializedName("is_loved_track")
    val isLovedTrack: Boolean? = false,
    @SerializedName("link")
    val link: String? = "",
    @SerializedName("md5_image")
    val md5Image: String? = "",
    @SerializedName("nb_tracks")
    val nbTracks: Int? = 0,
    @SerializedName("picture")
    val picture: String? = "",
    @SerializedName("picture_big")
    val pictureBig: String? = "",
    @SerializedName("picture_medium")
    val pictureMedium: String? = "",
    @SerializedName("picture_small")
    val pictureSmall: String? = "",
    @SerializedName("picture_type")
    val pictureType: String? = "",
    @SerializedName("picture_xl")
    val pictureXl: String? = "",
    @SerializedName("public")
    val `public`: Boolean? = false,
    @SerializedName("time_add")
    val timeAdd: Int? = 0,
    @SerializedName("time_mod")
    val timeMod: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("tracklist")
    val tracklist: String? = "",
    @SerializedName("type")
    val type: String? = ""
)