package com.oya.doubleq_client.pojo.cart.add

import com.google.gson.annotations.SerializedName

data class AddProductToCartModel(@SerializedName("unitPrice")
                                 val unitPrice: Double = 0.0,
                                 @SerializedName("total")
                                 val total: Double = 0.0,
                                 @SerializedName("unitPriceBeforeDiscount")
                                 val unitPriceBeforeDiscount: Double = 0.0,
                                 @SerializedName("color")
                                 val color: String = "",
                                 @SerializedName("user_id")
                                 val userId: String = "",
                                 @SerializedName("product_id")
                                 val productId: String = "",
                                 @SerializedName("qty")
                                 val qty: Int = 0,
                                 @SerializedName("totalDiscount")
                                 val totalDiscount: Double = 0.0,
                                 @SerializedName("_id")
                                 val Id: String = "",
                                 @SerializedName("createAt")
                                 val createAt: String = "")