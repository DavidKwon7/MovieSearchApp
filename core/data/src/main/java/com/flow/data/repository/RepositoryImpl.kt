package com.flow.data.repository

import androidx.paging.PagingData
import com.flow.data.mapper.MovieSearchDataDomainMapper
import com.flow.data.mapper.SearchDataDomainMapper
import com.flow.data.source.LocalDataSource
import com.flow.domain.entity.Item
import com.flow.data.source.Remote
import com.flow.domain.entity.SearchEntityModel
import com.flow.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remote: Remote,
    private val localDataSource: LocalDataSource,
    private val movieSearchDataDomainMapper: MovieSearchDataDomainMapper,
    private val searchDataDomainMapper: SearchDataDomainMapper
) : Repository {
    override suspend fun searchMovie(query: String): Flow<PagingData<Item>> {
           return remote.searchMovie(query)
    }

    override suspend fun insertSearch(data: SearchEntityModel) {
        return localDataSource.insertSearch(
            searchDataDomainMapper.to(data)
        )
    }

    override fun getAllSearch(): Flow<List<SearchEntityModel>> {
        return flow {
            val data = localDataSource.getAllSearch()
            emit(searchDataDomainMapper.fromList(data))
        }
    }
}