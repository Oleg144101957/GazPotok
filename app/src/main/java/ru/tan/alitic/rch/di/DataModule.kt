package ru.tan.alitic.rch.di

import android.content.Context
import com.moneocomp.tpao.data.RemoteConfigRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.tan.alitic.rch.data.AdjustAttrImpl
import ru.tan.alitic.rch.data.DataStoreImpl
import ru.tan.alitic.rch.data.GaidRepositoryImpl
import ru.tan.alitic.rch.data.InstallReferrerImpl
import ru.tan.alitic.rch.data.LoadingRepositoryImpl
import ru.tan.alitic.rch.data.NetworkCheckerRepositoryImpl
import ru.tan.alitic.rch.data.PostErrorRepositoryImpl
import ru.tan.alitic.rch.data.PushServiceInitializerImpl
import ru.tan.alitic.rch.domain.grey.AdjustAttr
import ru.tan.alitic.rch.domain.grey.DataStore
import ru.tan.alitic.rch.domain.grey.GaidRepository
import ru.tan.alitic.rch.domain.grey.InstallReferrer
import ru.tan.alitic.rch.domain.grey.LoadingRepository
import ru.tan.alitic.rch.domain.grey.NetworkCheckerRepository
import ru.tan.alitic.rch.domain.grey.PostErrorRepository
import ru.tan.alitic.rch.domain.grey.PushServiceInitializer
import ru.tan.alitic.rch.domain.grey.RemoteConfigRepo
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