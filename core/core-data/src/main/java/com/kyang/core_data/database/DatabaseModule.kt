package com.kyang.core_data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {
    fun providesTimeDatabase(@ApplicationContext context: Context): TimeDatabase =
        Room.databaseBuilder(
            context,
            TimeDatabase::class.java,
            "time_database"
        ).build()
}