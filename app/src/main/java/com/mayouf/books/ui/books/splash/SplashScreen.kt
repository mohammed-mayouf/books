package com.mayouf.books.ui.books.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import com.mayouf.books.ui.books.navigation.BooksDestinations
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    // Animate alpha from 0f to 1f
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1500), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        // Show splash for 2 seconds
        delay(2000)
        // Navigate to the BooksList route and clear backstack
        navController.navigate(BooksDestinations.BooksList.route) {
            popUpTo(BooksDestinations.Splash.route) { inclusive = true }
        }
    }

    // Splash content: you can show a logo or app name
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { alpha = alphaAnim.value },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "My Books App",
            style = MaterialTheme.typography.displaySmall
        )
    }
}
