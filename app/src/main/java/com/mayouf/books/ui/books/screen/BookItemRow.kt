package com.mayouf.books.ui.books.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mayouf.books.domain.model.Book

@Composable
fun BookItemRow(
    book: Book,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column {
            Text(text = book.title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = book.authors.joinToString(", "))
        }
    }
}
