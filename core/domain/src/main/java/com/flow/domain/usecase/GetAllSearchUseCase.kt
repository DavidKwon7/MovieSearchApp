package com.flow.domain.usecase

import com.flow.domain.entity.SearchEntityModel
import com.flow.domain.qualifiers.IoDispatcher
import com.flow.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllSearchUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    fun invoke(): Flow<List<SearchEntityModel>> {
        return repository.getAllSearch()
            .flowOn(dispatcher)
    }
}