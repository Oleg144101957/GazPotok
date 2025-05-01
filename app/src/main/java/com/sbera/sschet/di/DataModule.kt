package com.sbera.sschet.di

import android.content.Context
import com.sbera.sschet.data.DataStoreImpl
import com.sbera.sschet.data.NetworkCheckerRepositoryImpl
import com.sbera.sschet.data.RemoteConfigRepoImpl
import com.sbera.sschet.domain.DataStore
import com.sbera.sschet.domain.NetworkCheckerRepository
import com.sbera.sschet.domain.RemoteConfigRepo
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
    fun provideNetwork(@ApplicationContext context: Context): NetworkCheckerRepository {
        return NetworkCheckerRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideUserDataStorage(@ApplicationContext context: Context): DataStore {
        return DataStoreImpl(context)
    }

    @Provides
    @Singleton
    fun provideRemote(): RemoteConfigRepo {
        return RemoteConfigRepoImpl()
    }
}