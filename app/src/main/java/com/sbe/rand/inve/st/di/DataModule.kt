package com.sbe.rand.inve.st.di

import android.content.Context
import com.gosapp.sberkas.sber.data.AdjustAttrImpl
import com.gosapp.sberkas.sber.data.DataStoreImpl
import com.gosapp.sberkas.sber.data.GaidRepositoryImpl
import com.gosapp.sberkas.sber.data.InstallReferrerImpl
import com.gosapp.sberkas.sber.data.LoadingRepositoryImpl
import com.gosapp.sberkas.sber.data.NetworkCheckerRepositoryImpl
import com.gosapp.sberkas.sber.data.RemoteConfigRepoImpl
import com.gosapp.sberkas.sber.domain.AdjustAttr
import com.gosapp.sberkas.sber.domain.DataStore
import com.gosapp.sberkas.sber.domain.GaidRepository
import com.gosapp.sberkas.sber.domain.InstallReferrer
import com.gosapp.sberkas.sber.domain.LoadingRepository
import com.gosapp.sberkas.sber.domain.NetworkCheckerRepository
import com.gosapp.sberkas.sber.domain.RemoteConfigRepo
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