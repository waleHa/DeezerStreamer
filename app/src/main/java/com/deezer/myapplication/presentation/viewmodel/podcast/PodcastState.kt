package com.deezer.myapplication.presentation.viewmodel.podcast

import com.deezer.domain.remotemodel.podcast.PodcastItem

sealed class PodcastState {
    object Loading : PodcastState()
    data class Success(val podcasts: List<PodcastItem>) : PodcastState()
    data class Error(val message: String) : PodcastState()
}