package com.sb.er.act.iv.di

import android.content.Context
import com.gosapp.sberkas.sber.domain.AdjustAttr
import com.gosapp.sberkas.sber.domain.GaidRepository
import com.gosapp.sberkas.sber.domain.InstallReferrer
import com.gosapp.sberkas.sber.domain.LoadingRepository
import com.sb.er.act.iv.data.AdjustAttrImpl
import com.sb.er.act.iv.data.DataStoreImpl
import com.sb.er.act.iv.data.GaidRepositoryImpl
import com.sb.er.act.iv.data.InstallReferrerImpl
import com.sb.er.act.iv.data.LoadingRepositoryImpl
import com.sb.er.act.iv.data.NetworkCheckerRepositoryImpl
import com.sb.er.act.iv.data.PostErrorRepositoryImpl
import com.sb.er.act.iv.data.RemoteConfigRepoImpl
import com.sb.er.act.iv.domain.PostErrorRepository
import com.sb.er.act.iv.domain.grey.DataStore
import com.sb.er.act.iv.domain.grey.NetworkCheckerRepository
import com.sb.er.act.iv.domain.grey.RemoteConfigRepo
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
    fun provideAdjust(): AdjustAttr {
        return AdjustAttrImpl()
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

    @Provides
    @Singleton
    fun provideLoadingRepository(
        gaidRepository: GaidRepository,
        adjustAttr: AdjustAttr,
        remoteConfigRepoImpl: RemoteConfigRepoImpl,
        installReferrer: InstallReferrer,
        dataStore: DataStore
    ): LoadingRepository {
        return LoadingRepositoryImpl(
            gaidRepository,
            adjustAttr,
            remoteConfigRepoImpl,
            installReferrer, dataStore
        )
    }

    @Provides
    @Singleton
    fun providePostError(): PostErrorRepository {
        return PostErrorRepositoryImpl()
    }

}