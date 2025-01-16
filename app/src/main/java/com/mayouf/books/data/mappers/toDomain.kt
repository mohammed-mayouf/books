package com.mayouf.books.data.mappers


import com.mayouf.books.data.remote.model.BooksDTO
import com.mayouf.books.domain.model.Book

fun BooksDTO.toDomain(): Book {
    return Book(
        title = this.title.orEmpty(),
        authors = this.authorName ?: emptyList(),
        coverId = this.coverId
    )
}
