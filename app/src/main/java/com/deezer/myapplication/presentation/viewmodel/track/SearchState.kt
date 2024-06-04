package com.deezer.myapplication.presentation.viewmodel.track

import com.deezer.domain.remotemodel.track.SearchItem

sealed class SearchState {
    object Loading : SearchState()
    data class Success(val searchItems: List<SearchItem>) : SearchState()
    data class Error(val message: String) : SearchState()
}