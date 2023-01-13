package com.flow.data.source

import com.flow.data.local.SearchDAO
import com.flow.data.model.SearchDataModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val searchDAO: SearchDAO
) : LocalDataSource {
    override suspend fun insertSearch(data: List<SearchDataModel>) {
        return searchDAO.insertSearch(data)
    }

    override fun getAllSearch(): List<SearchDataModel> {
        return searchDAO.getAllSearch()
    }
}