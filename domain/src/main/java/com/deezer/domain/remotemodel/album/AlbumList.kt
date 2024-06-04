package com.deezer.domain.remotemodel.album


import com.deezer.domain.remotemodel.artist.Artist
import com.deezer.domain.remotemodel.track.TrackList
import com.google.gson.annotations.SerializedName

data class AlbumList(
    @SerializedName("artist")
    val artist: Artist? = Artist(),
    @SerializedName("available")
    val available: Boolean? = false,
    @SerializedName("contributors")
    val contributors: List<Contributor>? = listOf(),
    @SerializedName("cover")
    val cover: String? = "",
    @SerializedName("cover_big")
    val coverBig: String? = "",
    @SerializedName("cover_medium")
    val coverMedium: String? = "",
    @SerializedName("cover_small")
    val coverSmall: String? = "",
    @SerializedName("cover_xl")
    val coverXl: String? = "",
    @SerializedName("duration")
    val duration: Int? = 0,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int? = 0,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int? = 0,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean? = false,
    @SerializedName("fans")
    val fans: Int? = 0,
    @SerializedName("genre_id")
    val genreId: Int? = 0,
    @SerializedName("genres")
    val genres: List<GenreItem?>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("label")
    val label: String? = "",
    @SerializedName("link")
    val link: String? = "",
    @SerializedName("md5_image")
    val md5Image: String? = "",
    @SerializedName("nb_tracks")
    val nbTracks: Int? = 0,
    @SerializedName("record_type")
    val recordType: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("share")
    val share: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("tracklist")
    val tracklist: String? = "",
    @SerializedName("tracks")
    val trackList: TrackList? = TrackList(),
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("upc")
    val upc: String? = ""
)