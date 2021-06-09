package com.oya.doubleq_client.ui.a_welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.ActivityWelcomeBinding
import com.oya.doubleq_client.ui.a_login.LoginActivity
import com.oya.doubleq_client.ui.a_main.MainActivity

class Welcome : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityWelcomeBinding
    private var connectionDetector: ConnectionDetector? = null
    private lateinit var viewModel: WelcomeViewModel
    lateinit var welcomeList:ArrayList<WelcomeFrag>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)

        init()
        setUpViewModel()
        getWelcome()
        //actions
        binding.startBtn.setOnClickListener(this)
    }

    private fun init() {
        connectionDetector = ConnectionDetector(this)
        welcomeList = arrayListOf()
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(WelcomeViewModel::class.java)
    }
    private fun getWelcome() {
        if (!connectionDetector!!.isConnectingToInternet(this)) return

        viewModel.getWelcomes(this).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        HandleErrorMsg.errorMsg(this, it.data!!.message)
                        if (it.data.status) {
                            //Do something
                            for (i in it.data.constant!!) {
                                welcomeList.add(WelcomeFrag(i))
                            }
                            binding.pager.adapter = MyPagerAdapter(
                                supportFragmentManager
                            )
                            binding.indicator.setViewPager(binding.pager)
                            binding.pager.setCurrentItem(0, true)

                        }
                    }
                    Status.ERROR -> {//request error
                        binding.progressBar.visibility = View.GONE
                        HandleErrorMsg.errorMsg(this, it.message!!)
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }

            }
        })
    }

    inner class MyPagerAdapter(fragmentManager: FragmentManager?) :
        FragmentPagerAdapter(fragmentManager!!) {
        override fun getItem(position: Int): Fragment {
            return welcomeList[position]
        }

        override fun getCount(): Int {
            return welcomeList.size
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.startBtn->{
                SharedPref.setFirstTime(this,false)
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}