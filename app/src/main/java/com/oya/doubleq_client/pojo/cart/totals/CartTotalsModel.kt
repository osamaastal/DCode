package com.oya.doubleq_client.pojo.cart.totals

import com.google.gson.annotations.SerializedName

data class CartTotalsModel(@SerializedName("total_discount")
                           val totalDiscount: Double = 0.0,
                           @SerializedName("tax_value")
                           val taxValue: Double = 0.0,
                           @SerializedName("total_price")
                           val totalPrice: Double = 0.0,
                           @SerializedName("final_total")
                           val finalTotal: Double = 0.0,
                           @SerializedName("tax")
                           val tax: Double = 0.0,
                           @SerializedName("deliveryCost")
                           val deliveryCost: Double = 0.0)