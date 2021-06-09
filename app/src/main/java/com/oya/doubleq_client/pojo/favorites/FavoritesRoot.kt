package com.oya.doubleq_client.pojo.favorites

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.faq.Pagination
import com.oya.doubleq_client.pojo.product.ProductModel

data class FavoritesRoot(@SerializedName("status_code")
                         val statusCode: Int = 0,
                         @SerializedName("messageAr")
                         val messageAr: String = "",
                         @SerializedName("pagenation")
                         val pagenation: Pagination,
                         @SerializedName("messageEn")
                         val messageEn: String = "",
                         @SerializedName("items")
                         val items: List<ProductModel>?,
                         @SerializedName("status")
                         val status: Boolean = false)