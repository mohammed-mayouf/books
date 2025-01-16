package com.mayouf.books.ui.books.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mayouf.books.domain.model.Book

@Composable
fun BookDetailsSheet(book: Book) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = book.title)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = book.authors.joinToString(", "))

        Spacer(modifier = Modifier.height(16.dp))

        book.coverId?.let { coverId ->
            val coverUrl = "https://covers.openlibrary.org/b/id/${coverId}-M.jpg"
            AsyncImage(
                model = coverUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}
