package com.oya.doubleq_client.pojo.guest

import com.google.gson.annotations.SerializedName

data class GuestTokenRoot(@SerializedName("code")
                          val code: Int = 0,
                          @SerializedName("guest")
                          val guest: String = "",
                          @SerializedName("message")
                          val message: String = "",
                          @SerializedName("status")
                          val status: Boolean = false)