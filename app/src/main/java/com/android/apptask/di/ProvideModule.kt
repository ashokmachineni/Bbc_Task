package com.android.apptask.di

import com.android.apptask.data.remote.HeaderInterceptor
import com.android.apptask.data.remote.WebService
import com.android.apptask.data.repositories.FruitsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProvideModule {

    @Provides
    fun provideWebService(): WebService {
        return WebService.invoke(HeaderInterceptor())
    }

    @Provides
    fun provideFruitsRepositoryImpl(webService: WebService): FruitsRepositoryImpl {
        return FruitsRepositoryImpl(webService)
    }


}