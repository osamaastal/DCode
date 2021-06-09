package com.oya.doubleq_client.ui.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.data.api.MyHeaders
import com.oya.doubleq_client.data.api.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class ProfileViewModel(private val apiService: ApiService) : ViewModel() {
    private val TAG = "ProfileViewModel"

    fun getData(mContext: Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data =
                    apiService.getProfile(MyHeaders.get_token_lang(mContext))
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

    fun updateProfile(
        name: String, phone: String,
        image: MultipartBody.Part?, mContext: Context
    ) =
        liveData(Dispatchers.IO) {
            val body = hashMapOf<String, RequestBody>()
            body["phone_number"] = toRequestBody(phone)!!
            body["full_name"] = toRequestBody(name)!!

            Log.d(TAG, "phone_number: $phone")
//        body["image"] = SharedPref.getId(mContext)

            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        if (image != null)
                            apiService.updateProfile(
                                MyHeaders.get_token_lang(mContext),
                                body,
                                image
                            )
                        else
                            apiService.updateProfile(
                                MyHeaders.get_token_lang(mContext),
                                body
                            )
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

    private fun toRequestBody(value: String): RequestBody? {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }
}