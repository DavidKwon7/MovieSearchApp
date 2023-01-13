package com.flow.moviesearchapp.di

import com.flow.data.repository.RepositoryImpl
import com.flow.data.source.LocalDataSource
import com.flow.data.source.LocalDataSourceImpl
import com.flow.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

    @Binds
    abstract fun bindsLocalSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}