package com.oya.doubleq_client.pojo.types

import com.google.gson.annotations.SerializedName

data class TypesRoot(@SerializedName("code")
                     val code: Int = 0,
                     @SerializedName("message")
                     val message: String = "",
                     @SerializedName("items")
                     val items: List<TypeModel>?,
                     @SerializedName("status")
                     val status: Boolean = false)