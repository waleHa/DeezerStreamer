package com.deezer.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deezer.domain.UseCase.Tracks.SearchTracksUseCase
import com.deezer.domain.UseCase.User.GetAUserPlaylistsUseCase
import com.deezer.myapplication.presentation.intent.PlaylistIntent
import com.deezer.myapplication.presentation.intent.SearchIntent
import com.deezer.myapplication.presentation.state.PlaylistState
import com.deezer.myapplication.presentation.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchTracksUseCase: SearchTracksUseCase
) : ViewModel() {

    val intentChannel = Channel<SearchIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<SearchState>(SearchState.Loading)
    val state: StateFlow<SearchState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is SearchIntent.SearchTracks -> searchTracks(intent.query)
                }
            }
        }
    }

    private fun searchTracks(query: String) {
        viewModelScope.launch {
            _state.value = SearchState.Loading
            try {
                val searchItems = searchTracksUseCase(query)
                _state.value = SearchState.Success(searchItems)
            } catch (e: Exception) {
                _state.value = SearchState.Error(e.message ?: "An error occurred")
            }
        }
    }
}
