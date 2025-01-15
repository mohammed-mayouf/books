package com.mayouf.books.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mayouf.books.data.local.model.BookEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface BooksDao {
    @Query("SELECT * FROM books_table")
    fun getAllBooks(): Single<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<BookEntity>)
}
