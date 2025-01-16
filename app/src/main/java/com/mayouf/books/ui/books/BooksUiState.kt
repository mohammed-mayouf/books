package com.mayouf.books.ui.books

import com.mayouf.books.domain.model.Book

sealed class BooksUiState {
    object Loading : BooksUiState()
    data class Success(val books: List<Book>) : BooksUiState()
    data class Error(val message: String) : BooksUiState()
}
