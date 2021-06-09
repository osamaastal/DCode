package com.oya.doubleq_client.pojo.orders_product

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.sub_category.ColorsModel

data class OrderProduct_ProductModel(@SerializedName("unitPrice")
                                     val unitPrice: Double = 0.0,
                                     @SerializedName("image")
                                     val image: String = "",
                                     @SerializedName("total")
                                     val total: Double = 0.0,
                                     @SerializedName("unitPriceBeforeDiscount")
                                     val unitPriceBeforeDiscount: Double = 0.0,
                                     @SerializedName("color")
                                     val color: ColorsModel,
                                     @SerializedName("rate")
                                     val rate: Double = 0.0,
                                     @SerializedName("product_id")
                                     val productId: String = "",
                                     @SerializedName("qty")
                                     val qty: Int = 0,
                                     @SerializedName("name")
                                     val name: String = "",
                                     @SerializedName("totalDiscount")
                                     val totalDiscount: Double = 0.0,
                                     @SerializedName("details")
                                     val details: String = "",
                                     @SerializedName("_id")
                                     val Id: String = "")