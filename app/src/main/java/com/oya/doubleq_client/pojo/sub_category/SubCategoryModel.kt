package com.oya.doubleq_client.pojo.sub_category

import com.google.gson.annotations.SerializedName

data class SubCategoryModel(@SerializedName("image")
                            val image: String = "",
                            @SerializedName("code")
                            val code: String = "",
                            @SerializedName("name")
                            val name: String = "",
                            @SerializedName("details")
                            val details: String = "",
                            @SerializedName("_id")
                            val Id: String = "",
                            @SerializedName("colors")
                            val colors: List<ColorsModel>?)