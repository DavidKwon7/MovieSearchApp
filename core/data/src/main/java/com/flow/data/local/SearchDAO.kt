package com.flow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flow.data.model.SearchEntityModel

@Dao
interface SearchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(search: SearchEntityModel)

    @Query("SELECT * FROM searchDataModel")
    fun getAllSearch(): List<SearchEntityModel>
}