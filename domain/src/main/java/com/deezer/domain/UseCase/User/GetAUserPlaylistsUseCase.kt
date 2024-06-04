package com.deezer.domain.UseCase.User

import com.deezer.domain.remotemodel.playlist.PlaylistItem
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

class GetAUserPlaylistsUseCase @Inject constructor(private val repository: DeezerRepository) {
    suspend operator fun invoke(): List<PlaylistItem> = repository.getPlaylist().playlistItems?.filterNotNull() ?: emptyList<PlaylistItem>()
}