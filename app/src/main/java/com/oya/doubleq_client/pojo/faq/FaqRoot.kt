package com.oya.doubleq_client.pojo.faq

import com.google.gson.annotations.SerializedName

data class FaqRoot(@SerializedName("code")
                   val code: Int = 0,
                   @SerializedName("constant")
                   val constant: List<ConstantItem>?,
                   @SerializedName("pagination")
                   val pagination: Pagination,
                   @SerializedName("message")
                   val message: String = "",
                   @SerializedName("status")
                   val status: Boolean = false)