package com.oya.doubleq_client.pojo.favorites

import com.google.gson.annotations.SerializedName

data class ToggleFavoritesModel(@SerializedName("user_id")
                                val userId: String = "",
                                @SerializedName("product_id")
                                val productId: String = "",
                                @SerializedName("_id")
                                val Id: String = "",
                                @SerializedName("createAt")
                                val createAt: String = "")