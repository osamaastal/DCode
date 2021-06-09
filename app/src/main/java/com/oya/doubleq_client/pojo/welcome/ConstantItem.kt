package com.oya.doubleq_client.pojo.welcome

import com.google.gson.annotations.SerializedName

data class ConstantItem(@SerializedName("name")
                        val name: String = "",
                        @SerializedName("icon")
                        val icon: String = "",
                        @SerializedName("description")
                        val description: String = "",
                        @SerializedName("_id")
                        val Id: String = "")