package com.oya.doubleq_client.pojo.faq

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("pageNumber")
    val pageNumber: Int = 0,
    @SerializedName("size")
    val size: Int = 0,
    @SerializedName("totalPages")
    val totalPages: Int = 0,
    @SerializedName("totalElements")
    val totalElements: Int = 0
)