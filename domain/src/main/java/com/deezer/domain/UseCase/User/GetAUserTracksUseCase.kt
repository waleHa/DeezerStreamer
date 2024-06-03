package com.deezer.domain.UseCase.User

import android.util.Log
import com.deezer.domain.remotemodel.TrackItem
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

class GetAUserTracksUseCase @Inject constructor(private val repository: DeezerRepository) {
    suspend operator fun invoke(playlistID: String): List<TrackItem> {
        Log.i("TAG: GetAUserTracksUseCase", playlistID)
        return repository.getTracks(playlistID).trackItems?.filterNotNull() ?: emptyList()

    }
}