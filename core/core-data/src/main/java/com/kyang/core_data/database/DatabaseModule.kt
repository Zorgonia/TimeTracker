package com.kyang.core_data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt Module for DI of time database
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesTimeDatabase(@ApplicationContext context: Context): TimeDatabase =
        Room.databaseBuilder(
            context,
            TimeDatabase::class.java,
            "time_database"
        ).build()
}