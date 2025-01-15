package com.mayouf.books.data.remote.model

import com.google.gson.annotations.SerializedName

data class Books(
    @SerializedName("title") val title: String?,
    @SerializedName("author_name") val authorName: List<String>?,
    @SerializedName("cover_i") val coverId: Int?
)
