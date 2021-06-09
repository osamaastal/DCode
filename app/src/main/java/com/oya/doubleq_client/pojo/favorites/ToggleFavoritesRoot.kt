package com.oya.doubleq_client.pojo.favorites

import com.google.gson.annotations.SerializedName

data class ToggleFavoritesRoot(@SerializedName("status_code")
                               val statusCode: Int = 0,
                               @SerializedName("messageAr")
                               val messageAr: String = "",
                               @SerializedName("messageEn")
                               val messageEn: String = "",
                               @SerializedName("items")
                               val items: ToggleFavoritesModel,
                               @SerializedName("status")
                               val status: Boolean = false)