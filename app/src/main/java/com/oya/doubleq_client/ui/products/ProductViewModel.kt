package com.oya.doubleq_client.ui.products

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

class ProductViewModel(private val apiService: ApiService) : ViewModel() {

    fun toggleFavorite(mContext: Context, product_id:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val body = HashMap<String,Any>()
            body["product_id"] = product_id
            emit(Resource.success(data = apiService.toggleFavorites(MyHeaders.get_token_lang(mContext), body)))
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