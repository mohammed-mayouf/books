package com.mayouf.books.domain.model

data class Book(
    val title: String,
    val authors: List<String>,
    val coverId: Int?
)
