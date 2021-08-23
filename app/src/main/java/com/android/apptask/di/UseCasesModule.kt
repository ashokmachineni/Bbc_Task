package com.android.apptask.di

import com.android.apptask.domain.repositories.FruitsRepository
import com.android.apptask.domain.usecases.GetFruitsUseCase
import com.android.apptask.domain.usecases.LogStatsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetFruitsUseCase(fruitsRepository: FruitsRepository) =
        GetFruitsUseCase(fruitsRepository)

    @Provides
    fun provideLogStatsUseCase(fruitsRepository: FruitsRepository) =
        LogStatsUseCase(fruitsRepository)


}