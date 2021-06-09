package com.oya.doubleq_client.pojo.notifications

import com.google.gson.annotations.SerializedName

data class NotificationsItem(@SerializedName("formId")
                             val formId: String = "",
                             @SerializedName("msg")
                             val msg: String = "",
                             @SerializedName("date")
                             val date: String = "",
                             @SerializedName("user_id")
                             val userId: String = "",
                             @SerializedName("toName")
                             val toName: String = "",
                             @SerializedName("formName")
                             val formName: String = "",
                             @SerializedName("_id")
                             val Id: String = "",
                             @SerializedName("title")
                             val title: String = "",
                             @SerializedName("type")
                             val type: Int = 0,
                             @SerializedName("body_parms")
                             val bodyParms: String = "")