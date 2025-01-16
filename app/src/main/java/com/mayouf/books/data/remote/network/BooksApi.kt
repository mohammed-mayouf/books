package com.mayouf.books.data.remote.network

import com.mayouf.books.data.remote.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("search.json")
    fun searchBooks(
        @Query("q") query: String,
        @Query("fields") fields: String = "title,author_name,cover_i",
        @Query("limit") limit: Int = 10
    ): Single<SearchResponse>
}
