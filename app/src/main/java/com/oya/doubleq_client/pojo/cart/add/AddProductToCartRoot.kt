package com.oya.doubleq_client.pojo.cart.add

import com.google.gson.annotations.SerializedName

data class AddProductToCartRoot(@SerializedName("code")
                                val code: Int = 0,
                                @SerializedName("message")
                                val message: String = "",
                                @SerializedName("items")
                                val items: AddProductToCartModel,
                                @SerializedName("status")
                                val status: Boolean = false)