package com.mayouf.books

import com.mayouf.books.domain.repo.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [com.mayouf.books.di.RepositoryModule::class]
)
@Module
abstract class TestRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFakeBooksRepository(fake: FakeBooksRepository): BooksRepository
}
