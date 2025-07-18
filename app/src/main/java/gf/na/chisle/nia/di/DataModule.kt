package gf.na.chisle.nia.di

import android.content.Context
import gf.na.chisle.nia.data.AppsRepositoryImpl
import gf.na.chisle.nia.data.DataStoreImpl
import gf.na.chisle.nia.data.FBAttrProviderImpl
import gf.na.chisle.nia.data.GaidRepositoryImpl
import gf.na.chisle.nia.data.InstallReferrerImpl
import gf.na.chisle.nia.data.NetworkCheckerRepositoryImpl
import gf.na.chisle.nia.data.PostErrorRepositoryImpl
import gf.na.chisle.nia.data.PushServiceInitializerImpl
import gf.na.chisle.nia.data.RemoteConfigRepoImpl
import gf.na.chisle.nia.domain.grey.AppsRepository
import gf.na.chisle.nia.domain.grey.DataStore
import gf.na.chisle.nia.domain.grey.FBAttrProvider
import gf.na.chisle.nia.domain.grey.GaidRepository
import gf.na.chisle.nia.domain.grey.InstallReferrer
import gf.na.chisle.nia.domain.grey.NetworkCheckerRepository
import gf.na.chisle.nia.domain.grey.PostErrorRepository
import gf.na.chisle.nia.domain.grey.PushServiceInitializer
import gf.na.chisle.nia.domain.grey.RemoteConfigRepo
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