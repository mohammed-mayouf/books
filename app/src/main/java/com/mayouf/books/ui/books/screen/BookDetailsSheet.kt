package com.mayouf.books.ui.books.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mayouf.books.domain.model.Book

@Composable
fun BookDetailsSheet(book: Book) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .testTag("BookDetailsSheet")
    ) {
        book.coverId?.let { coverId ->
            val coverUrl = "https://covers.openlibrary.org/b/id/${coverId}-L.jpg"
            AsyncImage(
                model = coverUrl,
                contentDescription = "Cover image for ${book.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(
            text = book.title,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.authors.joinToString(", "),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
