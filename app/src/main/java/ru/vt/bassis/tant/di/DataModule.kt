package ru.vt.bassis.tant.di

import android.content.Context
import com.gosapp.sberkas.sber.domain.AdjustAttr
import com.gosapp.sberkas.sber.domain.GaidRepository
import com.gosapp.sberkas.sber.domain.InstallReferrer
import com.gosapp.sberkas.sber.domain.LoadingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vt.bassis.tant.data.AdjustAttrImpl
import ru.vt.bassis.tant.data.DataStoreImpl
import ru.vt.bassis.tant.data.GaidRepositoryImpl
import ru.vt.bassis.tant.data.InstallReferrerImpl
import ru.vt.bassis.tant.data.LoadingRepositoryImpl
import ru.vt.bassis.tant.data.NetworkCheckerRepositoryImpl
import ru.vt.bassis.tant.data.PostErrorRepositoryImpl
import ru.vt.bassis.tant.data.PushServiceInitializerImpl
import ru.vt.bassis.tant.data.RemoteConfigRepoImpl
import ru.vt.bassis.tant.domain.grey.DataStore
import ru.vt.bassis.tant.domain.grey.NetworkCheckerRepository
import ru.vt.bassis.tant.domain.grey.PushServiceInitializer
import ru.vt.bassis.tant.domain.grey.RemoteConfigRepo
import ru.vt.bassis.tant.iv.domain.PostErrorRepository
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
    fun providePostError(): PostErrorRepository {
        return PostErrorRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePush(): PushServiceInitializer {
        return PushServiceInitializerImpl()
    }

}