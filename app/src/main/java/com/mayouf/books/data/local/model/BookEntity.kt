package com.mayouf.books.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val authors: String,
    val coverId: Int?
)
