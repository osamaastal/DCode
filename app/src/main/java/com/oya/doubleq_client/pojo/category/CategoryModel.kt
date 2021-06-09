package com.oya.doubleq_client.pojo.category

import com.google.gson.annotations.SerializedName

data class CategoryModel(@SerializedName("image")
                     val image: String = "",
                         @SerializedName("name")
                     val name: String = "",
                         @SerializedName("details")
                     val details: String = "",
                         @SerializedName("_id")
                     val Id: String = "")