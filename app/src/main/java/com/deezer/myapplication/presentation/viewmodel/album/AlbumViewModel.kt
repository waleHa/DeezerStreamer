package com.deezer.myapplication.presentation.viewmodel.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deezer.domain.UseCase.album.GetAlbumByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase
) : ViewModel() {

    val intentChannel = Channel<AlbumDetailIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<AlbumDetailState>(AlbumDetailState.Loading)
    val state: StateFlow<AlbumDetailState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is AlbumDetailIntent.LoadAlbum -> loadAlbum(intent.albumId)
                }
            }
        }
    }

    private fun loadAlbum(albumId: String) {
        viewModelScope.launch {
            _state.value = AlbumDetailState.Loading
            try {
                val albumList = getAlbumByIdUseCase(albumId)
                _state.value = AlbumDetailState.Success(albumList)
            } catch (e: Exception) {
                _state.value = AlbumDetailState.Error(e.message ?: "An error occurred")
            }
        }
    }
}


