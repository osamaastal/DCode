package com.oya.doubleq_client.pojo.faq

import com.google.gson.annotations.SerializedName

data class ConstantItem(@SerializedName("name")
                        val name: String = "",
                        @SerializedName("details")
                        val details: String = "",
                        @SerializedName("_id")
                        val Id: String = "")