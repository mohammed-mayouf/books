package com.mayouf.books.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayouf.books.data.local.model.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}
