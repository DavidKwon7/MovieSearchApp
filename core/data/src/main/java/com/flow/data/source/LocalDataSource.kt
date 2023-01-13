package com.flow.data.source

import com.flow.data.model.SearchDataModel

interface LocalDataSource {

    suspend fun insertSearch(data: List<SearchDataModel>)

    fun getAllSearch(): List<SearchDataModel>
}