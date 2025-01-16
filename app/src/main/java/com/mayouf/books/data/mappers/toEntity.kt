package com.mayouf.books.data.mappers

import com.mayouf.books.data.local.model.BookEntity
import com.mayouf.books.domain.model.Book

fun Book.toEntity(): BookEntity {
    return BookEntity(
        title = this.title,
        authors = this.authors.joinToString(","),
        coverId = this.coverId
    )
}

fun BookEntity.toDomain(): Book {
    return Book(
        title = this.title,
        authors = this.authors.split(","),
        coverId = this.coverId
    )
}
