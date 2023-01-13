package com.flow.moviesearchapp.di

import android.content.Context
import androidx.room.Room
import com.flow.common.Constants
import com.flow.data.local.SearchDAO
import com.flow.data.local.SearchDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): SearchDataBase {
        return Room.databaseBuilder(context, SearchDataBase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(searchDataBase: SearchDataBase): SearchDAO {
        return searchDataBase.getSearch()
    }
}