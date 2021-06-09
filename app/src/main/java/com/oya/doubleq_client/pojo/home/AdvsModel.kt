package com.oya.doubleq_client.pojo.home

import com.google.gson.annotations.SerializedName

data class AdvsModel(@SerializedName("image")
                    val image: String = "",
                     @SerializedName("adv_for")
                    val advFor: String = "",
                     @SerializedName("category_id")
                    val categoryId: String = "",
                     @SerializedName("is_ads_redirect")
                    val isAdsRedirect: Boolean = false,
                     @SerializedName("product_id")
                    val productId: String = "",
                     @SerializedName("expiry_date")
                    val expiryDate: String = "",
                     @SerializedName("name")
                    val name: String = "",
                     @SerializedName("details")
                    val details: String = "",
                     @SerializedName("_id")
                    val Id: String = "",
                     @SerializedName("url")
                    val url: String = "")