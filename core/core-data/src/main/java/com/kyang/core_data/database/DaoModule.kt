package com.kyang.core_data.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt Module for DI of TimeDao
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideTimeDao(
        database: TimeDatabase
    ): TimeDao = database.timeDao()
}