package com.deezer.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deezer.domain.UseCase.User.GetAUserPlaylistsUseCase
import com.deezer.myapplication.presentation.intent.PlaylistIntent
import com.deezer.myapplication.presentation.state.PlaylistState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val getPlaylistsUseCase: com.deezer.domain.UseCase.User.GetAUserPlaylistsUseCase
) : ViewModel() {

    val intentChannel = Channel<PlaylistIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<PlaylistState>(PlaylistState.Loading)
    val state: StateFlow<PlaylistState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is PlaylistIntent.LoadPlaylists -> loadPlaylists()
                }
            }
        }
    }

    private fun loadPlaylists() {
        viewModelScope.launch {
            _state.value = PlaylistState.Loading
            try {
                val playlists = getPlaylistsUseCase()
                _state.value = PlaylistState.Success(playlists)
            } catch (e: Exception) {
                _state.value = PlaylistState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

