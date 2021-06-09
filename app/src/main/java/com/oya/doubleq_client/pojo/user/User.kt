package com.oya.doubleq_client.pojo.user

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("image")
                val image: String = "",
                @SerializedName("address")
                val address: String = "",
                @SerializedName("wallet")
                val wallet: Double = 0.0,
                @SerializedName("os")
                val os: String = "",
                @SerializedName("lng")
                val lng: Double = 0.0,
                @SerializedName("verify_code")
                val verifyCode: String = "",
                @SerializedName("createAt")
                val createAt: String = "",
                @SerializedName("isVerify")
                val isVerify: Boolean = false,
                @SerializedName("token")
                val token: String = "",
                @SerializedName("password")
                val password: String = "",
                @SerializedName("full_name")
                val fullName: String = "",
                @SerializedName("isDeleted")
                val isDeleted: Boolean = false,
                @SerializedName("phone_number")
                val phoneNumber: String = "",
                @SerializedName("_id")
                val Id: String = "",
                @SerializedName("isEnableNotifications")
                val isEnableNotifications: Boolean = false,
                @SerializedName("fcmToken")
                val fcmToken: String = "",
                @SerializedName("email")
                val email: String = "",
                @SerializedName("lat")
                val lat: Double = 0.0)