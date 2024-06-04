package com.deezer.myapplication.presentation.viewmodel.podcast

sealed class PodcastIntent {
    object LoadPodcasts : PodcastIntent()
}