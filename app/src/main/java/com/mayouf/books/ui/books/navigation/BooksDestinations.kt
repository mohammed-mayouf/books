package com.mayouf.books.ui.books.navigation

sealed class BooksDestinations(val route: String) {

    object Splash : BooksDestinations("splash")
    object BooksList : BooksDestinations("books_list")
}
