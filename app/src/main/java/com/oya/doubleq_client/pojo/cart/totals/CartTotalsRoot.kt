package com.oya.doubleq_client.pojo.cart.totals

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.cart.totals.CartTotalsModel

data class CartTotalsRoot(@SerializedName("code")
                          val code: Int = 0,
                          @SerializedName("message")
                          val message: String = "",
                          @SerializedName("items")
                          val items: CartTotalsModel,
                          @SerializedName("status")
                          val status: Boolean = false)