package com.flow.moviesearchapp.di

import com.flow.common.Mapper
import com.flow.data.mapper.MovieSearchDataDomainMapper
import com.flow.data.model.Item
import com.flow.domain.entity.MovieSearchEntityModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsMovieSearchDataDomainMapper(
        mapper: MovieSearchDataDomainMapper
    ): Mapper<Item, MovieSearchEntityModel>
}