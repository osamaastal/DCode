package com.oya.doubleq_client.pojo.cart.get

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.cart.totals.CartTotalsModel

data class CartModel(@SerializedName("totals")
                     val totals: CartTotalsModel,
                     @SerializedName("ptoducts")
                     val ptoducts: List<ProductsModelCart>?)