package com.kyang.core_data.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DaoModule {
    @Provides
    fun provideTimeDao(
        database: TimeDatabase
    ): TimeDao = database.timeDao()
}