package com.flow.moviesearchapp.di

import com.flow.common.Mapper
import com.flow.data.mapper.MovieSearchDataDomainMapper
import com.flow.domain.entity.Item
import com.flow.data.model.MovieSearchEntityModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

}