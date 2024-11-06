package com.example.disneypairsample.data.models

import com.example.disneypairsample.data.PicsumRepository
import com.example.disneypairsample.data.PicsumRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    internal abstract fun bindPicsumRepository(picsumRepositoryImpl: PicsumRepositoryImpl): PicsumRepository

}