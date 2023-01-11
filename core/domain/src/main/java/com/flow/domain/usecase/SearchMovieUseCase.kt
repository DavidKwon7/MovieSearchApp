package com.flow.domain.usecase

import androidx.paging.PagingData
import com.flow.domain.entity.Item
import com.flow.domain.qualifiers.IoDispatcher
import com.flow.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun invoke(query: String): Flow<PagingData<Item>> {
        return repository.searchMovie(query)
            .flowOn(dispatcher)
    }
}