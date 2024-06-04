package com.deezer.domain.UseCase.artist

import android.util.Log
import com.deezer.domain.remotemodel.artist.Artist
import com.deezer.domain.remotemodel.track.TrackItem
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

class GetArtistsByIdUseCase @Inject constructor(private val repository: DeezerRepository) {

    suspend operator fun invoke(artistId: String): Artist {
        return repository.getArtistsById(artistId)
    }

    suspend fun getTracks(artistId: String): List<TrackItem> {
        return repository.getArtistTracks(requireNotNull(artistId)).trackItems ?: emptyList()
    }
}