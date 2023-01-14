package com.flow.domain.repository

import androidx.paging.PagingData
import com.flow.domain.entity.Item
import com.flow.domain.entity.SearchEntityModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun searchMovie(query: String): Flow<PagingData<Item>>

    suspend fun insertSearch(data: SearchEntityModel)

    fun getAllSearch(): Flow<List<SearchEntityModel>>
}