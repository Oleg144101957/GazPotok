package com.gasin.est.vkl.di

import android.content.Context
import com.gasin.est.vkl.data.AppsRepositoryImpl
import com.gasin.est.vkl.data.DataStoreImpl
import com.gasin.est.vkl.data.FBAttrProviderImpl
import com.gasin.est.vkl.data.GaidRepositoryImpl
import com.gasin.est.vkl.data.InstallReferrerImpl
import com.gasin.est.vkl.data.NetworkCheckerRepositoryImpl
import com.gasin.est.vkl.data.PostErrorRepositoryImpl
import com.gasin.est.vkl.data.PushServiceInitializerImpl
import com.gasin.est.vkl.data.RemoteConfigRepoImpl
import com.gasin.est.vkl.domain.grey.AppsRepository
import com.gasin.est.vkl.domain.grey.DataStore
import com.gasin.est.vkl.domain.grey.FBAttrProvider
import com.gasin.est.vkl.domain.grey.GaidRepository
import com.gasin.est.vkl.domain.grey.InstallReferrer
import com.gasin.est.vkl.domain.grey.NetworkCheckerRepository
import com.gasin.est.vkl.domain.grey.PostErrorRepository
import com.gasin.est.vkl.domain.grey.PushServiceInitializer
import com.gasin.est.vkl.domain.grey.RemoteConfigRepo
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


    @Provides
    @Singleton
    fun provideGaid(@ApplicationContext context: Context): GaidRepository {
        return GaidRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun providePostError(): PostErrorRepository {
        return PostErrorRepositoryImpl()
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
    fun provideReferrer(@ApplicationContext context: Context): InstallReferrer {
        return InstallReferrerImpl(context)
    }

    @Provides
    @Singleton
    fun providePush(): PushServiceInitializer {
        return PushServiceInitializerImpl()
    }

}