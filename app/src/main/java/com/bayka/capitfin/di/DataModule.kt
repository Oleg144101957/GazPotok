package com.bayka.capitfin.di

import com.bayka.capitfin.data.RemoteConfigRepoImpl
import com.bayka.capitfin.domain.RemoteConfigRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePush(): RemoteConfigRepo {
        return RemoteConfigRepoImpl()
    }

}