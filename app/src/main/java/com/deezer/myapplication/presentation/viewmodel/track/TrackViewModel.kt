package com.deezer.myapplication.presentation.viewmodel.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val getTracksUseCase: com.deezer.domain.UseCase.User.GetAUserTracksUseCase
) : ViewModel() {

    val intentChannel = Channel<TrackIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<TrackState>(TrackState.Loading)
    val state: StateFlow<TrackState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is TrackIntent.LoadTracks -> loadTracks(intent.playlistId)
                }
            }
        }
    }

    private fun loadTracks(playlistId: String) {
        viewModelScope.launch {
            _state.value = TrackState.Loading
            try {
                val tracks = getTracksUseCase(playlistId)
                _state.value = TrackState.Success(tracks)
            } catch (e: Exception) {
                _state.value = TrackState.Error(e.message ?: "An error occurred")
            }
        }
    }
}