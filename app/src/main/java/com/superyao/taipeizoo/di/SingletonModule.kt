package com.superyao.taipeizoo.di

import android.annotation.SuppressLint
import android.app.Application
import com.superyao.taipeizoo.repository.DataRepository
import com.superyao.taipeizoo.repository.local.DataBase
import com.superyao.taipeizoo.repository.local.LocalDataSource
import com.superyao.taipeizoo.repository.remote.ApiService
import com.superyao.taipeizoo.repository.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    private fun baseOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CommonOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UnsafeOkHttpClient

    @Provides
    @Singleton
    @CommonOkHttpClient
    fun provideOkHttpClient(): OkHttpClient {
        return baseOkHttpBuilder().build()
    }

    @Provides
    @Singleton
    @UnsafeOkHttpClient
    @SuppressLint("TrustAllX509TrustManager")
    fun provideUnsafeOkHttpClient(): OkHttpClient {
        return try {
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory = sslContext.socketFactory
            baseOkHttpBuilder()
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true }
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            baseOkHttpBuilder().build()
        }
    }

    @Provides
    @Singleton
    fun provideApiService(@UnsafeOkHttpClient okHttpClient: OkHttpClient): ApiService {
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
        application: Application,
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): DataRepository {
        return DataRepository(application, remoteDataSource, localDataSource)
    }
}