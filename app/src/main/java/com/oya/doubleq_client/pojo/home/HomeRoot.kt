package com.oya.doubleq_client.pojo.home

import com.google.gson.annotations.SerializedName

data class HomeRoot(@SerializedName("code")
                    val code: Int = 0,
                    @SerializedName("message")
                    val message: String = "",
                    @SerializedName("items")
                    val items: HomeModel,
                    @SerializedName("status")
                    val status: Boolean = false)