package com.deezer.myapplication.presentation.state

import com.deezer.domain.remotemodel.SearchItem

sealed class SearchState {
    object Loading : SearchState()
    data class Success(val searchItems: List<SearchItem>) : SearchState()
    data class Error(val message: String) : SearchState()
}