package com.oya.doubleq_client.data.api

import com.oya.doubleq_client.pojo.cart.add.AddProductToCartRoot
import com.oya.doubleq_client.pojo.cart.count.CartCountRoot
import com.oya.doubleq_client.pojo.cart.get.CartRoot
import com.oya.doubleq_client.pojo.cart.totals.CartTotalsRoot
import com.oya.doubleq_client.pojo.cart.update.BodyUpdateProductCart
import com.oya.doubleq_client.pojo.category.CategoryRoot
import com.oya.doubleq_client.pojo.faq.FaqRoot
import com.oya.doubleq_client.pojo.favorites.FavoritesRoot
import com.oya.doubleq_client.pojo.favorites.ToggleFavoritesRoot
import com.oya.doubleq_client.pojo.general.GeneralRoot
import com.oya.doubleq_client.pojo.guest.GuestTokenRoot
import com.oya.doubleq_client.pojo.home.HomeRoot
import com.oya.doubleq_client.pojo.notifications.NotificationsRoot
import com.oya.doubleq_client.pojo.orders_product.OrdersProductRoot
import com.oya.doubleq_client.pojo.product.ProductsRoot
import com.oya.doubleq_client.pojo.product_details.ProductDetailsRoot
import com.oya.doubleq_client.pojo.search_filter.SearchRoot
import com.oya.doubleq_client.pojo.static_pages.StaticPageRoot
import com.oya.doubleq_client.pojo.sub_category.SubCategoryRoot
import com.oya.doubleq_client.pojo.types.TypesRoot
import com.oya.doubleq_client.pojo.user.UserRoot
import com.oya.doubleq_client.pojo.welcome.WelcomeRoot
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import kotlin.collections.HashMap


interface ApiService {
    @GET("mobile/constant/faq")
    suspend fun getFaq(@HeaderMap header: HashMap<String, String>): FaqRoot

    @GET("mobile/constant/static")
    suspend fun getStaticPage(
        @HeaderMap header: HashMap<String, String>,
        @Query("type") type: String
    ): StaticPageRoot

    @FormUrlEncoded //put this if @POST + has body
    @POST("mobile/constant/contact")
    suspend fun contactUs(
        @HeaderMap headerMap: HashMap<String, String>,
        @FieldMap body: HashMap<String, Any>
    ): GeneralRoot

    @FormUrlEncoded
    @POST("mobile/user/login")
    suspend fun login(
        @HeaderMap headerMap: HashMap<String, String>,
        @FieldMap body: HashMap<String, Any>
    ): UserRoot

    @FormUrlEncoded
    @POST("mobile/user/verify")
    suspend fun verCode(
        @HeaderMap headerMap: HashMap<String, String>,
        @FieldMap body: HashMap<String, Any>
    ): UserRoot

    @FormUrlEncoded
    @POST("mobile/user/resend")
    suspend fun resendCode(
        @HeaderMap headerMap: HashMap<String, String>,
        @FieldMap body: HashMap<String, Any>
    ): UserRoot

    @FormUrlEncoded
    @POST("mobile/user/fcm")
    suspend fun updateFcmToken(
        @HeaderMap headerMap: HashMap<String, String>,
        @FieldMap body: HashMap<String, Any>
    ): UserRoot

    @Multipart
//    @FormUrlEncoded
    @POST("mobile/user/update")
    suspend fun updateProfile(
        @HeaderMap headerMap: HashMap<String, String>,
        @PartMap body: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): UserRoot

    @Multipart
//    @FormUrlEncoded
    @POST("mobile/user/update")
    suspend fun updateProfile(
        @HeaderMap headerMap: HashMap<String, String>,
        @PartMap body: HashMap<String, RequestBody>
    ): UserRoot

    //    @FormUrlEncoded
    @POST("mobile/user/logout/{id}")
    suspend fun logout(
        @Path("id") id: String
    ): GeneralRoot

    @GET("mobile/user/profile")
    suspend fun getProfile(
        @HeaderMap headerMap: HashMap<String, String>
    ): UserRoot

    @GET("mobile/notifications/list")
    suspend fun getNotifications(
        @HeaderMap headerMap: HashMap<String, String>,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): NotificationsRoot

    @GET("mobile/constant/welcome")
    suspend fun getWelcome(
        @HeaderMap headerMap: HashMap<String, String>
    ): WelcomeRoot

    //    @FormUrlEncoded //put this if @POST + has body
    @POST("mobile/user/guest")
    suspend fun guestToken(): GuestTokenRoot//Form-encoded method must contain at least one @Field.

    @GET("mobile/constant/type")
    suspend fun getTypes(
        @HeaderMap headerMap: HashMap<String, String> // lang
    ): TypesRoot

    @GET("mobile/constant/category")
    suspend fun getCategory(
        @HeaderMap headerMap: HashMap<String, String>,// lang
        @Query("type_id") type_id: String
    ): CategoryRoot

    @GET("mobile/constant/subCategory")
    suspend fun getSubCategory(
        @HeaderMap headerMap: HashMap<String, String>,// lang
        @Query("category_id") category_id: String
    ): SubCategoryRoot

    @GET("mobile/user/home")
    suspend fun getHome(
        @HeaderMap headerMap: HashMap<String, String>// lang, token
    ): HomeRoot

    @GET("mobile/products/get")
    suspend fun getProducts(
        @HeaderMap headerMap: HashMap<String, String>,// lang, token
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("type") type: Int,
        @Query("object_id") object_id: String
    ): ProductsRoot

    @GET("mobile/products/search")
    suspend fun searchFilter(
        @HeaderMap headerMap: HashMap<String, String>,// lang, token
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("type") type: Int,
        @Query("object_id") object_id: String,
        @Query("search_value") search_value: String,
        @Query("color") color: String,
        @Query("tags") tags: String
    ): SearchRoot

    @GET("mobile/products/details")
    suspend fun getProductDetails(
        @HeaderMap headerMap: HashMap<String, String>,// lang, token
        @Query("product_id") product_id: String
    ): ProductDetailsRoot

    @GET("mobile/favorite/get")
    suspend fun getFavorites(
        @HeaderMap headerMap: HashMap<String, String>,// lang, token
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): FavoritesRoot

    @GET("mobile/cart/count")
    suspend fun getCartCount(
        @HeaderMap headerMap: HashMap<String, String>// lang, token
    ): CartCountRoot

    @GET("mobile/cart/total")
    suspend fun getCartTotals(
        @HeaderMap headerMap: HashMap<String, String>// lang, token
    ): CartTotalsRoot

    @GET("mobile/cart/get")
    suspend fun getCart(
        @HeaderMap headerMap: HashMap<String, String>// lang, token
    ): CartRoot

    @GET("mobile/order/get")
    suspend fun getOrdersProduct(
        @HeaderMap headerMap: HashMap<String, String>,// lang, token
        @Query("page") page:Int,
        @Query("limit") limit:Int,
        @Query("statusId") statusId:Int
    ): OrdersProductRoot

    @GET("mobile/order/get/{id}")
    suspend fun getOrderProductDetails(
        @HeaderMap headerMap: HashMap<String, String>,// lang, token
        @Path("id") id:String
    ): OrdersProductRoot

    @FormUrlEncoded
    @POST("mobile/order/rate/{id}")
    suspend fun rateOrder(
        @HeaderMap headerMap: HashMap<String, String>,//lang, token
        @FieldMap body: HashMap<String,Any>
    ): GeneralRoot

    @POST("mobile/cart/delete-all")
    suspend fun clearCart(
        @HeaderMap headerMap: HashMap<String, String>// lang, token
    ): GeneralRoot

    @POST("mobile/cart/delete-item/{id}")
    suspend fun deleteItemFromCart(
        @HeaderMap headerMap: HashMap<String, String>,// lang, token
        @Path("id") id: String
    ): GeneralRoot

    @FormUrlEncoded
    @POST("mobile/order/add")
    suspend fun addOrderProduct(
        @HeaderMap headerMap: HashMap<String, String>,//token, lang
        @FieldMap body: HashMap<String, Any>
    ): GeneralRoot // ibrahim retain another root

    @FormUrlEncoded
    @POST("mobile/favorite/toggle")
    suspend fun toggleFavorites(
        @HeaderMap headerMap: HashMap<String, String>,//token, lang
        @FieldMap body: HashMap<String, Any>
    ): ToggleFavoritesRoot

    @FormUrlEncoded
    @POST("mobile/cart/add")
    suspend fun addProductToCart(
        @HeaderMap headerMap: HashMap<String, String>,//token, lang
        @FieldMap body: HashMap<String, Any>
    ): AddProductToCartRoot

    @FormUrlEncoded
    @POST("mobile/cart/update")
    suspend fun updateProductInCart(// try this
        @HeaderMap headerMap: HashMap<String, String>,//token, lang
        @FieldMap body: BodyUpdateProductCart
    ): GeneralRoot
}