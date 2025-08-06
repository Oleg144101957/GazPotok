package ru.gz.po.tok41.di

import android.content.Context
import com.moneocomp.tpao.data.RemoteConfigRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.gz.po.tok41.data.AdjustAttrImpl
import ru.gz.po.tok41.data.DataStoreImpl
import ru.gz.po.tok41.data.GaidRepositoryImpl
import ru.gz.po.tok41.data.InstallReferrerImpl
import ru.gz.po.tok41.data.LoadingRepositoryImpl
import ru.gz.po.tok41.data.NetworkCheckerRepositoryImpl
import ru.gz.po.tok41.data.PostErrorRepositoryImpl
import ru.gz.po.tok41.data.PushServiceInitializerImpl
import ru.gz.po.tok41.domain.grey.AdjustAttr
import ru.gz.po.tok41.domain.grey.DataStore
import ru.gz.po.tok41.domain.grey.GaidRepository
import ru.gz.po.tok41.domain.grey.InstallReferrer
import ru.gz.po.tok41.domain.grey.LoadingRepository
import ru.gz.po.tok41.domain.grey.NetworkCheckerRepository
import ru.gz.po.tok41.domain.grey.PostErrorRepository
import ru.gz.po.tok41.domain.grey.PushServiceInitializer
import ru.gz.po.tok41.domain.grey.RemoteConfigRepo
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
    fun provideAdjust(): AdjustAttr {
        return AdjustAttrImpl()
    }


    @Provides
    @Singleton
    fun provideLoadingRepository(
        gaidRepository: GaidRepository,
        adjustAttr: AdjustAttr,
        remoteConfigRepoImpl: RemoteConfigRepoImpl,
        installReferrer: InstallReferrer,
        dataStore: DataStore,
        pushServiceInitializer: PushServiceInitializer
    ): LoadingRepository {
        return LoadingRepositoryImpl(
            gaidRepository,
            adjustAttr,
            remoteConfigRepoImpl,
            installReferrer,
            dataStore,
            pushServiceInitializer
        )
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
    fun provideReferrer(@ApplicationContext context: Context): InstallReferrer {
        return InstallReferrerImpl(context)
    }

    @Provides
    @Singleton
    fun providePush(): PushServiceInitializer {
        return PushServiceInitializerImpl()
    }

}