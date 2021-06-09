package com.oya.doubleq_client.pojo.user

import com.google.gson.annotations.SerializedName

data class UserRoot(@SerializedName("code")
                    val code: Int = 0,
                    @SerializedName("message")
                    val message: String = "",
                    @SerializedName("user")
                    val user: User,
                    @SerializedName("status")
                    val status: Boolean = false)