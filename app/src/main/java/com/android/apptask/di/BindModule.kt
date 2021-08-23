package com.android.apptask.di

import com.android.apptask.data.repositories.FruitsRepositoryImpl
import com.android.apptask.domain.repositories.FruitsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindFruitsRepository(fruitsRepositoryImpl: FruitsRepositoryImpl): FruitsRepository


}