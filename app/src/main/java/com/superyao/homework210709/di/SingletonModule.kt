package com.superyao.homework210709.di

import android.app.Application
import android.content.Context
import com.superyao.homework210709.repository.remote.ApiService
import com.superyao.homework210709.repository.DataRepository
import com.superyao.homework210709.repository.local.DataBase
import com.superyao.homework210709.repository.local.LocalDataSource
import com.superyao.homework210709.repository.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataBase(application: Application): DataBase {
        return DataBase.build(application)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dataBase: DataBase): LocalDataSource {
        return LocalDataSource(dataBase)
    }

    @Provides
    @Singleton
    fun provideDataRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): DataRepository {
        return DataRepository(remoteDataSource, localDataSource)
    }
}