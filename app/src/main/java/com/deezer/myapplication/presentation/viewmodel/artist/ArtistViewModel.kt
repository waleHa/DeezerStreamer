package com.deezer.myapplication.presentation.viewmodel.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deezer.domain.UseCase.artist.GetArtistsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getArtistsByIdUseCase: GetArtistsByIdUseCase
) : ViewModel() {

    val intentChannel = Channel<ArtistDetailIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<ArtistDetailState>(ArtistDetailState.Loading)
    val state: StateFlow<ArtistDetailState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is ArtistDetailIntent.LoadArtist -> loadArtist(intent.artistId)
                }
            }
        }
    }

    private fun loadArtist(artistId: String) {
        viewModelScope.launch {
            _state.value = ArtistDetailState.Loading
            try {
                val artist = getArtistsByIdUseCase(artistId)
                _state.value = ArtistDetailState.Success(artist)
            } catch (e: Exception) {
                _state.value = ArtistDetailState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

