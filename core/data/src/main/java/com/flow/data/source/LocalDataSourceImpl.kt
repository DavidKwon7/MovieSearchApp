package com.flow.data.source

import com.flow.data.local.SearchDAO
import com.flow.data.model.SearchEntityModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val searchDAO: SearchDAO
) : LocalDataSource {
    override suspend fun insertSearch(data: SearchEntityModel) {
        return searchDAO.insertSearch(data)
    }

    override fun getAllSearch(): List<SearchEntityModel> {
        return searchDAO.getAllSearch()
    }
}