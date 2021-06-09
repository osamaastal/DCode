package com.oya.doubleq_client.pojo.search_filter

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.faq.Pagination

data class SearchRoot(@SerializedName("status_code")
                      val statusCode: Int = 0,
                      @SerializedName("messageAr")
                      val messageAr: String = "",
                      @SerializedName("pagenation")
                      val pagenation: Pagination,
                      @SerializedName("messageEn")
                      val messageEn: String = "",
                      @SerializedName("items")
                      val items: List<ProductModelFull>?,
                      @SerializedName("status")
                      val status: Boolean = false)