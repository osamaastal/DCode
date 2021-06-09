package com.oya.doubleq_client.pojo.cart.count

import com.google.gson.annotations.SerializedName

data class CartCountRoot(@SerializedName("code")
                         val code: Int = 0,
                         @SerializedName("message")
                         val message: String = "",
                         @SerializedName("items")
                         val items: Int = 0,
                         @SerializedName("status")
                         val status: Boolean = false)