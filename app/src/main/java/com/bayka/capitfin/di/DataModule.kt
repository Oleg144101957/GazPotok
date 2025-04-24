package com.bayka.capitfin.di

import android.content.Context
import com.bayka.capitfin.data.DataStoreImpl
import com.bayka.capitfin.data.RemoteConfigRepoImpl
import com.bayka.capitfin.domain.DataStore
import com.bayka.capitfin.domain.RemoteConfigRepo
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
    fun provideRemote(): RemoteConfigRepo {
        return RemoteConfigRepoImpl()
    }

    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext context: Context): DataStore {
        return DataStoreImpl(context)
    }

}