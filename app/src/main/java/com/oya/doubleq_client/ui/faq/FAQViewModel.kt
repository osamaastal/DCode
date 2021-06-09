package com.oya.doubleq_client.ui.faq

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

class FAQViewModel(private val apiService: ApiService) : ViewModel() {
    private val TAG = "FAQViewModel"
    fun getData(context: Context) = liveData(Dispatchers.IO) {
//        val body:HashMap<String, Any> = hashMapOf()
//        body["phone"] =  phone
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getFaq(MyHeaders.get_lang(context))))
        } catch (exception: Exception) {
            try {
                val message = (exception as HttpException).response()!!.errorBody()!!.string()
                emit(Resource.error(data = null, message = message ?: "Error occurred"))
            }catch (e:Exception){
                Log.d(TAG, "exception: "+ exception.message+ "\ne: "+e.message)
            }

        }
    }
}