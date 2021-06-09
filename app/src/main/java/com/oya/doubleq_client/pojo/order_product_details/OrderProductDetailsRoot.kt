package com.oya.doubleq_client.pojo.order_product_details

import com.google.gson.annotations.SerializedName

data class OrderProductDetailsRoot(@SerializedName("code")
                                   val code: Int = 0,
                                   @SerializedName("message")
                                   val message: String = "",
                                   @SerializedName("items")
                                   val items: OrderProductDetailsModel,
                                   @SerializedName("status")
                                   val status: Boolean = false)