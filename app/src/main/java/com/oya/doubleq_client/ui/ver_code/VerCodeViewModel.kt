package com.oya.doubleq_client.ui.ver_code

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.data.api.MyHeaders
import com.oya.doubleq_client.data.api.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class VerCodeViewModel(private val apiService: ApiService) : ViewModel() {
    private val TAG = "LoginViewModel"

    fun verCode(phone:String,code:String, mContext:Context) = liveData(Dispatchers.IO) {
        val body = hashMapOf<String,Any>()
        body["phone_number"] = phone
        body["verify_code"] = code
        body["id"] = SharedPref.getId(mContext)

        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data =
                    apiService.verCode(MyHeaders.get_lang(mContext), body)
                )
            )
        } catch (exception: Exception) {
            try {
                val message = (exception as HttpException).response()!!.errorBody()!!.string()
                emit(Resource.error(data = null, message = message ?: "Error occurred"))
            } catch (e: Exception) {
                Log.d(TAG, "exception: " + exception.message + "\ne: " + e.message)
            }

        }
    }
    fun resendCode(mContext:Context) = liveData(Dispatchers.IO) {
        val body = hashMapOf<String,Any>()
        body["id"] = SharedPref.getId(mContext)

        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data =
                    apiService.resendCode(MyHeaders.get_lang(mContext), body)
                )
            )
        } catch (exception: Exception) {
            try {
                val message = (exception as HttpException).response()!!.errorBody()!!.string()
                emit(Resource.error(data = null, message = message ?: "Error occurred"))
            } catch (e: Exception) {
                Log.d(TAG, "exception: " + exception.message + "\ne: " + e.message)
            }

        }
    }
}