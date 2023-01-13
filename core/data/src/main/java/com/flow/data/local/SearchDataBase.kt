package com.flow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flow.data.model.SearchDataModel

@Database(
    entities = [SearchDataModel::class],
    version = 1
)
abstract class SearchDataBase : RoomDatabase() {
    abstract fun getSearch(): SearchDAO
}