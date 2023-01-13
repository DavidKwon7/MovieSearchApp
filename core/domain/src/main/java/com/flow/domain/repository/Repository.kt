package com.flow.domain.repository

import androidx.paging.PagingData
import com.flow.domain.entity.Item
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun searchMovie(query: String): Flow<PagingData<Item>>
}