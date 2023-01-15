package com.flow.data

import com.flow.data.model.SearchEntityModel

class TestDataGenerator {

    companion object {

        fun searchModel(): List<SearchEntityModel> {
            val item1 = SearchEntityModel(1, "title 1")
            val item2 = SearchEntityModel(2, "title 2")
            val item3 = SearchEntityModel(3, "title 3")
            return listOf(item1, item2, item3)
        }
    }
}