package com.oya.doubleq_client.pojo.welcome

import com.google.gson.annotations.SerializedName

data class WelcomeRoot(@SerializedName("code")
                       val code: Int = 0,
                       @SerializedName("constant")
                       val constant: List<ConstantItem>?,
                       @SerializedName("message")
                       val message: String = "",
                       @SerializedName("status")
                       val status: Boolean = false)