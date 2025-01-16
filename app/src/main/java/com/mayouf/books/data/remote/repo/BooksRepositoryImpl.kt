package com.mayouf.books.data.remote.repo

import com.mayouf.books.data.local.BooksDao
import com.mayouf.books.data.mappers.toDomain
import com.mayouf.books.data.mappers.toEntity
import com.mayouf.books.data.remote.network.BooksApi
import com.mayouf.books.domain.model.Book
import com.mayouf.books.domain.repo.BooksRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksApi: BooksApi,
    private val booksDao: BooksDao
) : BooksRepository {

    override fun getBooks(query: String): Single<List<Book>> {
        return booksDao.getAllBooks()
            .flatMap { localList ->
                if (localList.isNotEmpty()) {
                    refreshFromRemote(query).subscribeOn(Schedulers.io()).subscribe()
                    Single.just(localList.map { it.toDomain() })
                } else {
                    refreshFromRemote(query)
                        .andThen(booksDao.getAllBooks())
                        .map { entities -> entities.map { it.toDomain() } }
                }
            }
            .subscribeOn(Schedulers.io())
    }

    private fun refreshFromRemote(query: String): Completable {
        return booksApi.searchBooks(query)
            .map { response ->
                response.docs?.map { dto -> dto.toDomain() }.orEmpty()
            }
            .doOnSuccess { domainBooks ->
                val entities = domainBooks.map { it.toEntity() }
                booksDao.insertAll(entities)
            }
            .ignoreElement()
    }
}
