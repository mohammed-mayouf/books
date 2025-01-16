package com.mayouf.books.ui.books.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mayouf.books.ui.books.BooksViewModel
import com.mayouf.books.ui.books.screen.BooksListScreen
import com.mayouf.books.ui.books.splash.SplashScreen

@Composable
fun BooksNavGraph(
    navController: NavHostController,
    booksViewModel: BooksViewModel,
    startDestination: String = BooksDestinations.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(BooksDestinations.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = BooksDestinations.BooksList.route) {
            BooksListScreen(navController = navController, viewModel = booksViewModel)
        }
    }
}
