package com.oya.doubleq_client.pojo.orders_product

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.faq.Pagination

data class OrdersProductRoot(@SerializedName("code")
                      val code: Int = 0,
                             @SerializedName("pagination")
                      val pagination: Pagination,
                             @SerializedName("message")
                      val message: String = "",
                             @SerializedName("items")
                      val items: List<OrderProductModel>?,
                             @SerializedName("status")
                      val status: Boolean = false)