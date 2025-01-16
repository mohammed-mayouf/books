package com.mayouf.books.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("numFound")
    val numFound: Int?,
    @SerializedName("docs")
    val docs: List<BooksDTO>?
)
