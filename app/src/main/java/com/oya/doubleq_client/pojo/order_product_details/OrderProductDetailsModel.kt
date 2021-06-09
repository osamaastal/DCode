package com.oya.doubleq_client.pojo.order_product_details

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.orders_product.OrderProduct_ProductModel

data class OrderProductDetailsModel(@SerializedName("tax")
                                    val tax: Double = 0.0,
                                    @SerializedName("createAt")
                                    val createAt: String = "",
                                    @SerializedName("reason_id")
                                    val reasonId: String = "",
                                    @SerializedName("total")
                                    val total: Double = 0.0,
                                    @SerializedName("statusId")
                                    val statusId: Int = 0,
                                    @SerializedName("user_id")
                                    val userId: String = "",
                                    @SerializedName("canceled_note")
                                    val canceledNote: String = "",
                                    @SerializedName("employee_id")
                                    val employeeId: EmployeeId,
                                    @SerializedName("totalDiscount")
                                    val totalDiscount: Double = 0.0,
                                    @SerializedName("_id")
                                    val Id: String = "",
                                    @SerializedName("netTotal")
                                    val netTotal: Double = 0.0,
                                    @SerializedName("items")
                                    val items: List<OrderProduct_ProductModel>?,
                                    @SerializedName("deliveryCost")
                                    val deliveryCost: Double = 0.0)