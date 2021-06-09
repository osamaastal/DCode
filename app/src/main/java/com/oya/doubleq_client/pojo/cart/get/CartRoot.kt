package com.oya.doubleq_client.pojo.cart.get

import com.google.gson.annotations.SerializedName

data class CartRoot(@SerializedName("code")
                    val code: Int = 0,
                    @SerializedName("message")
                    val message: String = "",
                    @SerializedName("items")
                    val items: CartModel,
                    @SerializedName("status")
                    val status: Boolean = false)