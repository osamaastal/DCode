package com.oya.doubleq_client.ui.contact_us

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.data.api.MyHeaders
import com.oya.doubleq_client.data.api.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class ContactUsViewModel(private val apiService: ApiService) : ViewModel() {
    private val TAG = "ContactUsViewModel"
    fun contactUs( title: String, details: String,context: Context) = liveData(Dispatchers.IO) {
        val body: HashMap<String, Any> = hashMapOf()
        body["Title"] = title
        body["details"] = details
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = apiService
                        .contactUs(MyHeaders.get_token_lang(context), body)
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