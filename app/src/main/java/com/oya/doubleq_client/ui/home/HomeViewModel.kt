package com.oya.doubleq_client.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.data.api.MyHeaders
import com.oya.doubleq_client.data.api.Resource
import com.oya.doubleq_client.pojo.home.HomeModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class HomeViewModel(private val apiService: ApiService) : ViewModel() {

    fun getHome(mContext: Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getHome(MyHeaders.get_token_lang(mContext))))
        } catch (exception: Exception) {
            try {
                val message = (exception as HttpException).response()!!.errorBody()!!.string()
                emit(Resource.error(data = null, message = message ?: "Error occurred"))
            }catch (ignore:Exception){

            }

        }
    }
//    companion object{
        val mutableLiveDataHomeModel = MutableLiveData<HomeModel>()
        fun updateModel(homeModel: HomeModel){
            mutableLiveDataHomeModel.value = homeModel
        }
//    }


}