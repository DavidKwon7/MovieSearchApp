package com.flow.domain.usecase

import com.flow.domain.entity.SearchEntityModel
import com.flow.domain.repository.Repository
import javax.inject.Inject

class InsertSearchUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke(data: SearchEntityModel) {
        return repository.insertSearch(data)

    }
}