package com.deezer.domain.UseCase.podcast

import com.deezer.domain.remotemodel.podcast.PodcastItem
import com.deezer.domain.remotemodel.podcast.PodcastList
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

// Define a UseCase for fetching Podcasts
class GetPodcastUseCase @Inject constructor(private val repository: DeezerRepository) {
    suspend operator fun invoke(): List<PodcastItem> = repository.getPodcast().data?.filterNotNull() ?: emptyList()
}
