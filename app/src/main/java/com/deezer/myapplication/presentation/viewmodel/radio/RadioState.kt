package com.deezer.myapplication.presentation.viewmodel.radio

import com.deezer.domain.remotemodel.radio.RadioItem

sealed class RadioState {
    object Loading : RadioState()
    data class Success(val radios: List<RadioItem>) : RadioState()
    data class Error(val message: String) : RadioState()
}