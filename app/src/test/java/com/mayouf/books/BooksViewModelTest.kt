package com.mayouf.books.ui.books

import com.mayouf.books.domain.model.Book
import com.mayouf.books.domain.repo.BooksRepository
import com.mayouf.books.domain.usecase.GetBooksUseCase
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class BooksViewModelTest {

    private lateinit var searchBooksUseCase: GetBooksUseCase
    private lateinit var viewModel: BooksViewModel

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitComputationSchedulerHandler { Schedulers.trampoline() }
        val fakeRepository = mock(BooksRepository::class.java)
        val fakeBooks = listOf(
            Book(title = "Mock Book A", authors = listOf("Author A"), coverId = 101),
            Book(title = "Mock Book B", authors = listOf("Author B"), coverId = 202)
        )
        `when`(fakeRepository.getBooks(anyString()))
            .thenReturn(Single.just(fakeBooks).subscribeOn(Schedulers.trampoline()))

        searchBooksUseCase = GetBooksUseCase(fakeRepository)
        viewModel = BooksViewModel(searchBooksUseCase)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testSearchBooksSuccess() {
        viewModel.searchBooks("dummy query")

        val uiState = viewModel.uiState.value
        assertTrue("Expected Success state", uiState is BooksUiState.Success)
        if (uiState is BooksUiState.Success) {
            assertTrue(uiState.books.size == 2)
        }
    }
}
