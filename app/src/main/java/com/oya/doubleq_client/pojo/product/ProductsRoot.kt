package com.oya.doubleq_client.pojo.product

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.faq.Pagination

data class ProductsRoot(@SerializedName("code")
                        val code: Int = 0,
                        @SerializedName("pagination")
                        val pagination: Pagination,
                        @SerializedName("message")
                        val message: String = "",
                        @SerializedName("items")
                        val items: List<ProductModel>?,
                        @SerializedName("status")
                        val status: Boolean = false)