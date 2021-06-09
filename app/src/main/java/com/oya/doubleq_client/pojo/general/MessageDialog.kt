package com.oya.doubleq_client.pojo.general


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class MessageDialog(
    @SerializedName("show")
    val show: Boolean
) : Parcelable