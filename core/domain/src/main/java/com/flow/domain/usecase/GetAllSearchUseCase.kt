package com.flow.domain.usecase

import com.flow.domain.entity.SearchEntityModel
import com.flow.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSearchUseCase @Inject constructor(
    private val repository: Repository
) {
    fun invoke(): Flow<List<SearchEntityModel>> {
        return repository.getAllSearch()
    }
}