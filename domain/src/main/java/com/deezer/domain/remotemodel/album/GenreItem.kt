package com.deezer.domain.remotemodel.album


import com.google.gson.annotations.SerializedName

data class GenreItem(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("picture")
    val picture: String? = "",
    @SerializedName("type")
    val type: String? = ""
)