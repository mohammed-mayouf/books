package com.mayouf.books.di

import com.mayouf.books.data.remote.repo.BooksRepositoryImpl
import com.mayouf.books.domain.repo.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BooksRepository
}
