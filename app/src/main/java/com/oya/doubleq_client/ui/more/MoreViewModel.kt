package com.oya.doubleq_client.ui.more

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.data.api.MyHeaders
import com.oya.doubleq_client.data.api.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class MoreViewModel(private val apiService: ApiService) : ViewModel() {
    private val TAG = "viewModel_"
    fun logout(mContext:Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data =
                    apiService.logout(SharedPref.getId(mContext))
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