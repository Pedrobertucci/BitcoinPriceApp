package com.bitcoin.viewer.di

import com.bitcoin.viewer.BuildConfig
import com.bitcoin.viewer.remoteDataSource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providerRemoteDataSource(): RemoteDataSource {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RemoteDataSource::class.java)
    }
}