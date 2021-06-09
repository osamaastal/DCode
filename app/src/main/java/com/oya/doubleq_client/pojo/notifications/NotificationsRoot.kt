package com.oya.doubleq_client.pojo.notifications

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.faq.Pagination

data class NotificationsRoot(@SerializedName("code")
                             val code: Int = 0,
                             @SerializedName("pagination")
                             val pagination: Pagination,
                             @SerializedName("message")
                             val message: String = "",
                             @SerializedName("notifications")
                             val notifications: List<NotificationsItem>?,
                             @SerializedName("status")
                             val status: Boolean = false)