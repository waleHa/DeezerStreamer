package com.deezer.domain.UseCase.Tracks

import com.deezer.domain.remotemodel.track.SearchItem
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

class SearchTracksUseCase @Inject constructor(private val repository: DeezerRepository) {
    suspend operator fun invoke(query: String): List<SearchItem> = repository.searchTracks(query).searchItems?.filterNotNull() ?: emptyList()
}