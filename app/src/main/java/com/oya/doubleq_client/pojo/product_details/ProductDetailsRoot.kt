package com.oya.doubleq_client.pojo.product_details

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.search_filter.ProductModelFull

data class ProductDetailsRoot(@SerializedName("status_code")
                              val statusCode: Int = 0,
                              @SerializedName("messageAr")
                              val messageAr: String = "",
                              @SerializedName("messageEn")
                              val messageEn: String = "",
                              @SerializedName("items")
                              val items: ProductModelFull,
                              @SerializedName("status")
                              val status: Boolean = false)