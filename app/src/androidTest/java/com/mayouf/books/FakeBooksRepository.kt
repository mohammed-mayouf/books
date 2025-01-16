package com.mayouf.books

import com.mayouf.books.domain.model.Book
import com.mayouf.books.domain.repo.BooksRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeBooksRepository @Inject constructor() : BooksRepository {
    override fun getBooks(query: String): Single<List<Book>> {
        val fakeBooks = listOf(
            Book(title = "Mock Book A", authors = listOf("Author A"), coverId = 101),
            Book(title = "Mock Book B", authors = listOf("Author B"), coverId = 202)
        )
        return Single.just(fakeBooks)
    }
}
