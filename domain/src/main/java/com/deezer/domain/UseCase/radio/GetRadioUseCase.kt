package com.deezer.domain.UseCase.radio

import com.deezer.domain.remotemodel.radio.RadioItem
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

class GetRadioUseCase @Inject constructor(private val repository: DeezerRepository) {
    suspend operator fun invoke(): List<RadioItem> {
        return repository.getRadio().data
    }
}
