package com.flow.data.source

import com.flow.data.model.SearchEntityModel

interface LocalDataSource {

    suspend fun insertSearch(data: SearchEntityModel)

    fun getAllSearch(): List<SearchEntityModel>
}