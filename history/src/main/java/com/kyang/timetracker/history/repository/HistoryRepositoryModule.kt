package com.kyang.timetracker.history.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module for DI of HistoryRepository
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryRepositoryModule {
    @Binds
    abstract fun bindHistoryRepository(
        historyRepository: DatabaseHistoryRepository
    ): HistoryRepository
}