package com.oya.doubleq_client.pojo.category

import com.google.gson.annotations.SerializedName

data class CategoryRoot(@SerializedName("code")
                        val code: Int = 0,
                        @SerializedName("message")
                        val message: String = "",
                        @SerializedName("items")
                        val items: List<CategoryModel>?,
                        @SerializedName("status")
                        val status: Boolean = false)