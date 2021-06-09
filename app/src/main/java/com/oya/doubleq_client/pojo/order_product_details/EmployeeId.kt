package com.oya.doubleq_client.pojo.order_product_details

import com.google.gson.annotations.SerializedName

data class EmployeeId(@SerializedName("image")
                      val image: String = "",
                      @SerializedName("commercialId")
                      val commercialId: String = "",
                      @SerializedName("level")
                      val level: String = "",
                      @SerializedName("social")
                      val social: String = "",
                      @SerializedName("available")
                      val available: Boolean = false,
                      @SerializedName("orderPercentage")
                      val orderPercentage: Double = 0.0,
                      @SerializedName("full_name")
                      val fullName: String = "",
                      @SerializedName("isDeleted")
                      val isDeleted: Boolean = false,
                      @SerializedName("rate")
                      val rate: Double = 0.0,
                      @SerializedName("phone_number")
                      val phoneNumber: String = "",
                      @SerializedName("_id")
                      val Id: String = "",
                      @SerializedName("email")
                      val email: String = "",
                      @SerializedName("age")
                      val age: String = "",
                      @SerializedName("IdNo")
                      val idNo: String = "")