package org.care.packie.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.care.packie.data.source.LocalStuffsDataSource
import org.care.packie.data.source.StuffsDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalStuffsDataSource (
        localStuffsDataSource: LocalStuffsDataSource
    ): StuffsDataSource
}