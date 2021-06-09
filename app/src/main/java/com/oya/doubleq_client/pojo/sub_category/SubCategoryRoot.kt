package com.oya.doubleq_client.pojo.sub_category

import com.google.gson.annotations.SerializedName

data class SubCategoryRoot(@SerializedName("code")
                           val code: Int = 0,
                           @SerializedName("message")
                           val message: String = "",
                           @SerializedName("items")
                           val items: List<SubCategoryModel>?,
                           @SerializedName("status")
                           val status: Boolean = false)