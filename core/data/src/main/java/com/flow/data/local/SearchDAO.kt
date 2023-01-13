package com.flow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flow.data.model.SearchDataModel

@Dao
interface SearchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: List<SearchDataModel>)

    @Query("SELECT * FROM searchDataModel")
    fun getAllSearch(): List<SearchDataModel>
}