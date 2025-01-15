package com.mayouf.books.domain.usecase

import com.mayouf.books.domain.model.Book
import com.mayouf.books.domain.repo.BooksRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    operator fun invoke(query: String): Single<List<Book>> {
        return repository.getBooks(query)
    }
}
