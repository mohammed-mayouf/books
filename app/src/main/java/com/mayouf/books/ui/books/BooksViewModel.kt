package com.mayouf.books.ui.books

import androidx.lifecycle.ViewModel
import com.mayouf.books.domain.usecase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val uiState: StateFlow<BooksUiState> = _uiState

    private val compositeDisposable = CompositeDisposable()

    init {
        searchBooks("the lord of the rings")
    }

    fun searchBooks(query: String) {
        _uiState.value = BooksUiState.Loading
        val disposable = getBooksUseCase(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ books ->
                _uiState.value = BooksUiState.Success(books)
            }, { error ->
                _uiState.value = BooksUiState.Error(error.message ?: "Unknown error")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
