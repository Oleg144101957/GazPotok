package com.qua.ntum.barh.at.di

import android.content.Context
import com.qua.ntum.barh.at.data.AppsRepositoryImpl
import com.qua.ntum.barh.at.data.DataStoreImpl
import com.qua.ntum.barh.at.data.FBAttrProviderImpl
import com.qua.ntum.barh.at.data.GaidRepositoryImpl
import com.qua.ntum.barh.at.data.InstallReferrerImpl
import com.qua.ntum.barh.at.data.NetworkCheckerRepositoryImpl
import com.qua.ntum.barh.at.data.PostErrorRepositoryImpl
import com.qua.ntum.barh.at.data.PushServiceInitializerImpl
import com.qua.ntum.barh.at.data.RemoteConfigRepoImpl
import com.qua.ntum.barh.at.domain.grey.AppsRepository
import com.qua.ntum.barh.at.domain.grey.DataStore
import com.qua.ntum.barh.at.domain.grey.FBAttrProvider
import com.qua.ntum.barh.at.domain.grey.GaidRepository
import com.qua.ntum.barh.at.domain.grey.InstallReferrer
import com.qua.ntum.barh.at.domain.grey.NetworkCheckerRepository
import com.qua.ntum.barh.at.domain.grey.PostErrorRepository
import com.qua.ntum.barh.at.domain.grey.PushServiceInitializer
import com.qua.ntum.barh.at.domain.grey.RemoteConfigRepo
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