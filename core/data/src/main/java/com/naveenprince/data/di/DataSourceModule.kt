package com.naveenprince.data.di

import com.naveenprince.data.source.remote.SportsNewsDataSource
import com.naveenprince.data.source.remote.SportsNewsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Naveen.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule2 {

    @Singleton
    @Binds
    abstract fun bindSportsNewsDataSource(sportsNewsDataSourceImpl: SportsNewsDataSourceImpl):
            SportsNewsDataSource

}
