package org.care.packie.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.care.packie.data.repository.CategoryRepositoryImpl
import org.care.packie.domain.CategoryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        repositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository
}