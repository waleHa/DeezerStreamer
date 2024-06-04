package com.deezer.myapplication.presentation.viewmodel.radio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deezer.domain.UseCase.radio.GetRadioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RadioViewModel @Inject constructor(
    private val getRadioUseCase: GetRadioUseCase
) : ViewModel() {

    val intentChannel = Channel<RadioIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<RadioState>(RadioState.Loading)
    val state: StateFlow<RadioState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is RadioIntent.LoadRadios -> loadRadios()
                }
            }
        }
    }

    private fun loadRadios() {
        viewModelScope.launch {
            _state.value = RadioState.Loading
            try {
                val radios = getRadioUseCase()
                _state.value = RadioState.Success(radios)
            } catch (e: Exception) {
                _state.value = RadioState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

