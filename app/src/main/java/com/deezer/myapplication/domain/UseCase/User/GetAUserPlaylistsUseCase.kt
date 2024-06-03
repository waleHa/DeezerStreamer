package com.deezer.myapplication.domain.UseCase.User

import com.deezer.myapplication.data.remote.model.PlaylistItem
import com.deezer.myapplication.domain.repository.DeezerRepository
import javax.inject.Inject

class GetAUserPlaylistsUseCase @Inject constructor(private val repository: DeezerRepository) {
    suspend operator fun invoke(): List<PlaylistItem> = repository.getPlaylist().playlistItems?.filterNotNull() ?: emptyList<PlaylistItem>()
}