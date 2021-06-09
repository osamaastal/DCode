package com.oya.doubleq_client.ui.a_login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.oya.doubleq_client.R
import com.oya.doubleq_client.databinding.ActivityLoginBinding
import com.oya.doubleq_client.ui.a_main.MainActivity
import com.oya.doubleq_client.ui.a_other.OtherActivity
import com.oya.doubleq_client.ui.login.Login

class LoginActivity : AppCompatActivity() {
    private var fragment: Fragment? = null
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        fragment = Login()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment!!)
            .commit()
    }
}