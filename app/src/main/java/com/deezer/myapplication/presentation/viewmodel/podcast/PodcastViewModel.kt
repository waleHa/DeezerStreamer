package com.deezer.myapplication.presentation.viewmodel.podcast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deezer.domain.UseCase.podcast.GetPodcastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val getPodcastUseCase: GetPodcastUseCase
) : ViewModel() {

    val intentChannel = Channel<PodcastIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<PodcastState>(PodcastState.Loading)
    val state: StateFlow<PodcastState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is PodcastIntent.LoadPodcasts -> loadPodcasts()
                }
            }
        }
    }

    private fun loadPodcasts() {
        viewModelScope.launch {
            _state.value = PodcastState.Loading
            try {
                val podcasts = getPodcastUseCase()
                _state.value = PodcastState.Success(podcasts)
            } catch (e: Exception) {
                _state.value = PodcastState.Error(e.message ?: "An error occurred")
            }
        }
    }
}
