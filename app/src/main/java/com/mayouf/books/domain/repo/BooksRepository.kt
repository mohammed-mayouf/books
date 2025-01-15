package com.mayouf.books.domain.repo

import com.mayouf.books.domain.model.Book
import io.reactivex.rxjava3.core.Single

interface BooksRepository {
    fun getBooks(query: String): Single<List<Book>>
}
