package com.trao.dev.tech.di

import android.content.Context
import com.trao.dev.tech.data.DataStoreImpl
import com.trao.dev.tech.domain.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext context: Context): DataStore {
        return DataStoreImpl(context)
    }
}