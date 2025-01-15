package com.mayouf.books.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("docs") val docs: List<Books>?
)
