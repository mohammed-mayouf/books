package com.mayouf.books

import com.mayouf.books.data.local.BooksDao
import com.mayouf.books.data.local.model.BookEntity
import com.mayouf.books.data.remote.model.BooksDTO
import com.mayouf.books.data.remote.model.SearchResponse
import com.mayouf.books.data.remote.network.BooksApi
import com.mayouf.books.data.remote.repo.BooksRepositoryImpl
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever

class BooksRepositoryImplTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }

    private lateinit var booksApi: BooksApi
    private lateinit var booksDao: BooksDao
    private lateinit var repository: BooksRepositoryImpl

    @Before
    fun setup() {
        booksApi = mock(BooksApi::class.java)
        booksDao = mock(BooksDao::class.java)
        repository = BooksRepositoryImpl(booksApi, booksDao)
    }

    @Test
    fun `searchBooks when DB is empty should fetch remote and store then return list`() {
        val fakeDto = BooksDTO(
            title = "Test Book",
            authorName = listOf("Test Author"),
            coverId = 111
        )
        val searchResponse = SearchResponse(docs = listOf(fakeDto), numFound = 10)

        whenever(booksApi.searchBooks(anyString(), eq("title,author_name,cover_i"), eq(10)))
            .thenReturn(Single.just(searchResponse).subscribeOn(Schedulers.io()))

        val fakeEntity =
            BookEntity(id = 1, title = "Test Book", authors = "Test Author", coverId = 111)
        whenever(booksDao.getAllBooks())
            .thenReturn(Single.just(emptyList()), Single.just(listOf(fakeEntity)))

        doNothing().`when`(booksDao).insertAll(any())

        val testObserver = repository.getBooks("query").test()

        testObserver.assertNoErrors()
        testObserver.assertValue { books ->
            books.size == 1 &&
                    books[0].title == "Test Book" &&
                    books[0].authors.contains("Test Author")
        }
    }

    @Test
    fun `searchBooks when DB has data should return DB data and refresh in background`() {
        val fakeEntity = BookEntity(id = 1, title = "DB Book", authors = "DB Author", coverId = 222)
        whenever(booksDao.getAllBooks()).thenReturn(Single.just(listOf(fakeEntity)))

        val fakeDto = BooksDTO(
            title = "Remote Book",
            authorName = listOf("Remote Author"),
            coverId = 333
        )
        val searchResponse = SearchResponse(docs = listOf(fakeDto), numFound = 10)
        whenever(booksApi.searchBooks(anyString(), eq("title,author_name,cover_i"), eq(10)))
            .thenReturn(Single.just(searchResponse).subscribeOn(Schedulers.io()))

        val testObserver = repository.getBooks("query").test()

        testObserver.assertNoErrors()
        testObserver.assertValue { books ->
            books.size == 1 && books[0].title == "DB Book"
        }
        verify(booksApi).searchBooks(anyString(), eq("title,author_name,cover_i"), eq(10))
    }
}
