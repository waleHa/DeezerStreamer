package com.deezer.data.remote.di

import com.deezer.data.repository.DeezerRepositoryImp
import com.deezer.domain.repository.DeezerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepositoryInterface(repositoryImp: DeezerRepositoryImp): DeezerRepository
}