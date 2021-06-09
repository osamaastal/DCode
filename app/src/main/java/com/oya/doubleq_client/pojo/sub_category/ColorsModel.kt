package com.oya.doubleq_client.pojo.sub_category

import com.google.gson.annotations.SerializedName

data class ColorsModel(
    @SerializedName("isDeleted")
    val isDeleted: Boolean = false,
    @SerializedName("enName")
    val enName: String = "",
    @SerializedName("_id")
    val Id: String = "",
    @SerializedName("arName")
    val arName: String = "",
    @SerializedName("isChecked")//local
    var isChecked: Boolean = false
)