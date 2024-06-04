package com.deezer.domain.UseCase.album

import android.util.Log
import com.deezer.domain.remotemodel.album.Album
import com.deezer.domain.remotemodel.album.AlbumList
import com.deezer.domain.repository.DeezerRepository
import javax.inject.Inject

class GetAlbumByIdUseCase @Inject constructor(private val repository: DeezerRepository) {
    suspend operator fun invoke(albumId: String): AlbumList {
        Log.i("TAG:GetAlbumByIdUseCase", albumId)
        return repository.getAlbumById(albumId)
    }
}
