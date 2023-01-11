package com.flow.data.repository

import androidx.paging.PagingData
import com.flow.common.Mapper
import com.flow.domain.entity.Item
import com.flow.data.source.Remote
import com.flow.data.model.MovieSearchEntityModel
import com.flow.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remote: Remote,
) : Repository {
    override suspend fun searchMovie(query: String): Flow<PagingData<Item>> {
           return remote.searchMovie(query)
    }
}