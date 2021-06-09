package com.oya.doubleq_client.pojo.types

import com.google.gson.annotations.SerializedName

data class TypeModel(@SerializedName("image")
                     val image: String = "",
                     @SerializedName("color")
                     val color: String = "",
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("details")
                     val details: String = "",
                     @SerializedName("_id")
                     val Id: String = "")