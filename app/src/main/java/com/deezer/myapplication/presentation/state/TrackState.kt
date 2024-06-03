package com.deezer.myapplication.presentation.state

import com.deezer.domain.remotemodel.TrackItem

sealed class TrackState {
    data object Loading : TrackState()
    data class Success(val tracks: List<TrackItem>) : TrackState()
    data class Error(val message: String) : TrackState()
}