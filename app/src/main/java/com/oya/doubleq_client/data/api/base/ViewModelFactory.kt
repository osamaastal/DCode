package com.oya.doubleq_client.data.api.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.ui.a_splash.SplashViewModel
import com.oya.doubleq_client.ui.a_welcome.WelcomeViewModel
import com.oya.doubleq_client.ui.contact_us.ContactUsViewModel
import com.oya.doubleq_client.ui.faq.FAQViewModel
import com.oya.doubleq_client.ui.home.HomeViewModel
import com.oya.doubleq_client.ui.login.LoginViewModel
import com.oya.doubleq_client.ui.more.MoreViewModel
import com.oya.doubleq_client.ui.notifications.NotificationsViewModel
import com.oya.doubleq_client.ui.policy.StaticPageViewModel
import com.oya.doubleq_client.ui.product_details.ProductDetailsViewModel
import com.oya.doubleq_client.ui.products.ProductViewModel
import com.oya.doubleq_client.ui.profile.ProfileViewModel
import com.oya.doubleq_client.ui.ver_code.VerCodeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(FAQViewModel::class.java)){
            return FAQViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(StaticPageViewModel::class.java)){
            return StaticPageViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(ContactUsViewModel::class.java)){
            return ContactUsViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(VerCodeViewModel::class.java)){
            return VerCodeViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(SplashViewModel::class.java)){
            return SplashViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(MoreViewModel::class.java)){
            return MoreViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(NotificationsViewModel::class.java)){
            return NotificationsViewModel(apiService)as T
        }
        if(modelClass.isAssignableFrom(WelcomeViewModel::class.java)){
            return WelcomeViewModel(apiService) as T
        }
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(apiService) as T
        }
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(apiService) as T
        }
        if(modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)){
            return ProductDetailsViewModel(apiService) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}