package com.oya.doubleq_client.pojo.home

import com.google.gson.annotations.SerializedName

data class UnitModel(@SerializedName("showTitle")
                  val showTitle: String = "",
                     @SerializedName("name")
                  val name: String = "",
                     @SerializedName("_id")
                  val Id: String = "")