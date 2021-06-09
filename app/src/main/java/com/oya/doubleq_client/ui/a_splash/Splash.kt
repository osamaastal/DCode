package com.oya.doubleq_client.ui.a_splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.google.firebase.messaging.FirebaseMessaging
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.data.api.HandleErrorMsg
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.ConnectionDetector
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.ActivitySplashBinding
import com.oya.doubleq_client.ui.a_login.LoginActivity
import com.oya.doubleq_client.data.api.ApiClient
import com.oya.doubleq_client.data.api.Status
import com.oya.doubleq_client.ui.a_main.MainActivity
import com.oya.doubleq_client.ui.a_welcome.Welcome
import java.util.*

class Splash : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null
    private var connectionDetector: ConnectionDetector? = null
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
//        TestFairy.begin(this, "SDK-y6DpNhJa")
        val sp: SharedPreferences = getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE)
        var language = sp.getString(cons.language, Locale.getDefault().language)
        if (language != cons.AR && language != cons.EN) {
            language = cons.AR
        }
        setLocale(language!!)
        init()
        //viewModel
        setUpViewModel()
        updateFcmToken()

        Handler().postDelayed({
            intent = when {
                SharedPref.getFirstTime(this) -> Intent(this, Welcome::class.java)
                sp.getString(cons.id, "") == "" -> Intent(this, LoginActivity::class.java)
                else -> Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 1500)
        val animation =
            AnimationUtils.loadAnimation(this, R.anim.logo_animation)
        binding!!.logo.animation = animation
    }

    private fun init() {
        connectionDetector = ConnectionDetector(this)
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(SplashViewModel::class.java)
    }

    private fun updateFcmToken() {
        if (SharedPref.getToken(this) == "")
            return

        if (!connectionDetector!!.isConnectingToInternet(this)) return
        val fcmToken = FirebaseMessaging.getInstance().token.result
        viewModel.updateFcmToken(fcmToken, this).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        HandleErrorMsg.errorMsg(this, it.data!!.message)
                        if (it.data.status) {
                            //Do something

                        }
                    }
                    Status.ERROR -> {//request error
                        HandleErrorMsg.errorMsg(this, it.message!!)
                    }
                    Status.LOADING -> {
                    }
                }

            }
        })
    }

    //
    private fun setLocale(language: String) {
        val locale = Locale(language)
        val config =
            Configuration(resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }
}