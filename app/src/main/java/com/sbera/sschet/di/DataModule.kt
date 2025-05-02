package com.sbera.sschet.di

import android.content.Context
import com.sbera.sschet.data.AdjustAttrImpl
import com.sbera.sschet.data.DataStoreImpl
import com.sbera.sschet.data.GaidRepositoryImpl
import com.sbera.sschet.data.InstallReferrerImpl
import com.sbera.sschet.data.LoadingRepositoryImpl
import com.sbera.sschet.data.NetworkCheckerRepositoryImpl
import com.sbera.sschet.data.RemoteConfigRepoImpl
import com.sbera.sschet.domain.AdjustAttr
import com.sbera.sschet.domain.DataStore
import com.sbera.sschet.domain.GaidRepository
import com.sbera.sschet.domain.InstallReferrer
import com.sbera.sschet.domain.LoadingRepository
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
}