package com.gos.inv.est.info.di

import android.content.Context
import com.gos.inv.est.info.data.AppsRepositoryImpl
import com.gos.inv.est.info.data.DataStoreImpl
import com.gos.inv.est.info.data.FBAttrProviderImpl
import com.gos.inv.est.info.data.GaidRepositoryImpl
import com.gos.inv.est.info.data.InstallReferrerImpl
import com.gos.inv.est.info.data.NetworkCheckerRepositoryImpl
import com.gos.inv.est.info.data.PostErrorRepositoryImpl
import com.gos.inv.est.info.data.PushServiceInitializerImpl
import com.gos.inv.est.info.data.RemoteConfigRepoImpl
import com.gos.inv.est.info.domain.grey.AppsRepository
import com.gos.inv.est.info.domain.grey.DataStore
import com.gos.inv.est.info.domain.grey.FBAttrProvider
import com.gos.inv.est.info.domain.grey.GaidRepository
import com.gos.inv.est.info.domain.grey.InstallReferrer
import com.gos.inv.est.info.domain.grey.NetworkCheckerRepository
import com.gos.inv.est.info.domain.grey.PostErrorRepository
import com.gos.inv.est.info.domain.grey.PushServiceInitializer
import com.gos.inv.est.info.domain.grey.RemoteConfigRepo
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