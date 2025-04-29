package com.trao.dev.tech.di

import android.content.Context
import com.trao.dev.tech.data.AppsRepositoryImpl
import com.trao.dev.tech.data.DataStoreImpl
import com.trao.dev.tech.data.FBAttrProviderImpl
import com.trao.dev.tech.data.GaidRepositoryImpl
import com.trao.dev.tech.data.InstallReferrerImpl
import com.trao.dev.tech.data.NetworkCheckerRepositoryImpl
import com.trao.dev.tech.data.PushServiceInitializerImpl
import com.trao.dev.tech.data.RemoteConfigRepoImpl
import com.trao.dev.tech.domain.AppsRepository
import com.trao.dev.tech.domain.DataStore
import com.trao.dev.tech.domain.FBAttrProvider
import com.trao.dev.tech.domain.GaidRepository
import com.trao.dev.tech.domain.InstallReferrer
import com.trao.dev.tech.domain.NetworkCheckerRepository
import com.trao.dev.tech.domain.PushServiceInitializer
import com.trao.dev.tech.domain.RemoteConfigRepo
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
    fun providePush(): PushServiceInitializer {
        return PushServiceInitializerImpl()
    }

    @Provides
    @Singleton
    fun provideApps(): AppsRepository {
        return AppsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideFB(): FBAttrProvider {
        return FBAttrProviderImpl()
    }

    @Provides
    @Singleton
    fun provideRemote(): RemoteConfigRepo {
        return RemoteConfigRepoImpl()
    }


    @Provides
    @Singleton
    fun provideGaid(@ApplicationContext context: Context): GaidRepository {
        return GaidRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideReferrer(@ApplicationContext context: Context): InstallReferrer {
        return InstallReferrerImpl(context)
    }
}