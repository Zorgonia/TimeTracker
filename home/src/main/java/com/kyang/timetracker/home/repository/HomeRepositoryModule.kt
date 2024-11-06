package com.kyang.timetracker.home.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Hilt Module for the home repository.
 */
@InstallIn(ViewModelComponent::class)
@Module
abstract class HomeRepositoryModule {

    @Binds
    internal abstract fun bindsHomeRepository(
        homeRepository: HomeRepositoryImpl
    ): HomeRepository
}