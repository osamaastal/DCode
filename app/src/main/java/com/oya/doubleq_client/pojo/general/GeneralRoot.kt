package com.oya.doubleq_client.pojo.general


import com.google.gson.annotations.SerializedName

//@SuppressLint("ParcelCreator")
data class GeneralRoot(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: Boolean = false
)