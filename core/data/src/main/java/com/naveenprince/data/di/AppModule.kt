package com.naveenprince.data.di

import com.naveenprince.data.api.SportsNewsApi
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
    fun provideSportsNewsApi(): SportsNewsApi {
        return Retrofit.Builder()
            .baseUrl(SportsNewsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

}