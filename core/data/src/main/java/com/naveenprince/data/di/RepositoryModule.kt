package com.naveenprince.data.di

import com.naveenprince.data.repository.SportsNewsRepository
import com.naveenprince.data.repository.SportsNewsRepositoryImpl
import com.naveenprince.data.source.remote.SportsNewsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency Injection class for Repository module
 *
 * Created by Naveen.
 */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSportsNewsRepository(sportsNewsDataSource: SportsNewsDataSource): SportsNewsRepository =
        SportsNewsRepositoryImpl(sportsNewsDataSource)
}