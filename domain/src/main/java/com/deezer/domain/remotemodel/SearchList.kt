package com.deezer.domain.remotemodel


import com.google.gson.annotations.SerializedName

data class SearchList(
    @SerializedName("data")
    val searchItems: List<SearchItem>? = listOf(),
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("total")
    val total: Int? = 0
)