package com.oya.doubleq_client.pojo.static_pages

import com.google.gson.annotations.SerializedName

data class Constant(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("_id")
                    val Id: String = "",
                    @SerializedName("type")
                    val type: String = "",
                    @SerializedName("content")
                    val content: String = "")