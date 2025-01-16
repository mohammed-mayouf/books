package com.mayouf.books

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mayouf.books.data.local.BooksDao
import com.mayouf.books.data.local.BooksDatabase
import com.mayouf.books.data.local.model.BookEntity
import com.mayouf.books.data.mappers.toDomain
import com.mayouf.books.domain.model.Book
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BooksDaoTest {


    private lateinit var database: BooksDatabase
    private lateinit var booksDao: BooksDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BooksDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        booksDao = database.booksDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveBooks_success() {
        val bookEntity1 =
            BookEntity(title = "Room Test Book", authors = "Test Author", coverId = 100)
        val bookEntity2 =
            BookEntity(title = "Second Book", authors = "Another Author", coverId = 200)

        booksDao.insertAll(listOf(bookEntity1, bookEntity2))
        val retrieved = booksDao.getAllBooks().blockingGet()

        assertEquals(2, retrieved.size)
        val domainBooks: List<Book> = retrieved.map { it.toDomain() }
        assertEquals("Room Test Book", domainBooks[0].title)
    }
}
