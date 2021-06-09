package com.oya.doubleq_client.ui.product_details

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

class ProductDetailsViewModel(private val apiService: ApiService) : ViewModel() {

    fun getProductDetails(mContext: Context, product_id:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getProductDetails(MyHeaders.get_token_lang(mContext), product_id)))
        } catch (exception: Exception) {
            try {
                val message = (exception as HttpException).response()!!.errorBody()!!.string()
                emit(Resource.error(data = null, message = message ?: "Error occurred"))
            }catch (ignore:Exception){

            }

        }
    }
    fun addToCart(mContext: Context, product_id:String, qty:Int, color_id:String) = liveData(Dispatchers.IO) {
        val body = HashMap<String,Any>()
        body["product_id"] = product_id
        body["qty"] = qty
        body["color"] = color_id
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.addProductToCart(MyHeaders.get_token_lang(mContext), body)))
        } catch (exception: Exception) {
            try {
                val message = (exception as HttpException).response()!!.errorBody()!!.string()
                emit(Resource.error(data = null, message = message ?: "Error occurred"))
            }catch (ignore:Exception){

            }

        }
    }
//    companion object{
        val mutableLiveDataModel= MutableLiveData<HomeModel>()
        fun updateModel(homeModel: HomeModel){
            mutableLiveDataModel.value = homeModel
        }
//    }


}