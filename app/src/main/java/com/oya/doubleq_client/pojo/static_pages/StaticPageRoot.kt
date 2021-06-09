package com.oya.doubleq_client.pojo.static_pages

import com.google.gson.annotations.SerializedName

data class StaticPageRoot(@SerializedName("code")
                          val code: Int = 0,
                          @SerializedName("constant")
                          val constant: Constant,
                          @SerializedName("message")
                          val message: String = "",
                          @SerializedName("status")
                          val status: Boolean = false)