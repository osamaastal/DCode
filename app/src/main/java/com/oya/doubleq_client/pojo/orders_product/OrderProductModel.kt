package com.oya.doubleq_client.pojo.orders_product

import com.google.gson.annotations.SerializedName

data class OrderProductModel(@SerializedName("total")
                             val total: Double = 0.0,
                             @SerializedName("statusId")
                             val statusId: Int = 0,
                             @SerializedName("user_id")
                             val userId: String = "",
                             @SerializedName("totalDiscount")
                             val totalDiscount: Double = 0.0,
                             @SerializedName("items")
                             val Products: List<OrderProduct_ProductModel>?,
                             @SerializedName("tax")
                             val tax: Double = 0.0,
                             @SerializedName("_id")
                             val Id: String = "",
                             @SerializedName("netTotal")
                             val netTotal: Double = 0.0,
                             @SerializedName("createAt")
                             val createAt: String = "",
                             @SerializedName("deliveryCost")
                             val deliveryCost: Double = 0.0)