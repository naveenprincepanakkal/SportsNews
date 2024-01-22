package com.naveenprince.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Dependency Injection class for overall app data module
 *
 * Created by Naveen.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSportsNewsApi(): com.naveenprince.data.api.SportsNewsApi {
        return Retrofit.Builder()
            .baseUrl(com.naveenprince.data.api.SportsNewsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

}