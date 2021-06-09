package com.oya.doubleq_client.ui.ver_code

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.data.api.HandleErrorMsg

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.databinding.FragmentVerCodeBinding
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.ui.a_login.LoginActivity
import com.oya.doubleq_client.ui.a_main.MainActivity
import com.oya.doubleq_client.data.api.ApiClient
import com.oya.doubleq_client.data.api.Status

class VerCode : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentVerCodeBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: LoginActivity? = null
    private lateinit var phone_with_intro: String

    //view model
    private lateinit var viewModel: VerCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ver_code, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions
        binding.backBtn.setOnClickListener(this)
        binding.verifyBtn.setOnClickListener(this)
        binding.resendTv.setOnClickListener(this)
        return view
    }

    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        binding.phoneTv.text = phone_with_intro
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(VerCodeViewModel::class.java)
    }

    private fun verCode() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return

        viewModel.verCode(phone_with_intro, binding.pinView.text.toString(), mContext!!)
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            showProgress(false)
                            HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)
                            if (it.data.status) {
                                //Do something
                                SharedPref.setUserData(it.data.user, mContext)
                                val intent = Intent(mContext, MainActivity::class.java)
                                startActivity(intent)
                                requireActivity().finish()
                            }
                        }
                        Status.ERROR -> {//request error
                            showProgress(false)
                            HandleErrorMsg.errorMsg(mContext!!, it.message!!)
                        }
                        Status.LOADING -> {
                            showProgress(true)
                        }
                    }

                }
            })
    }
    private fun resendCode() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return

        viewModel.resendCode(mContext!!)
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            showProgress(false)
                            HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)
                            if (it.data.status) {
                                //Do something
                                TestMsg.show(mContext,""+it.data.user.verifyCode,0)
                            }
                        }
                        Status.ERROR -> {//request error
                            showProgress(false)
                            HandleErrorMsg.errorMsg(mContext!!, it.message!!)
                        }
                        Status.LOADING -> {
                            showProgress(true)
                        }
                    }

                }
            })
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.backBtn -> requireActivity().onBackPressed()
            R.id.verifyBtn -> {
                if (isEmpty())
                    return
                verCode()
            }
            R.id.resendTv->{
                resendCode()
                binding.linearResend.visibility = View.GONE
            }

        }
    }

    private fun isEmpty(): Boolean {
        if (binding.pinView.text.isNullOrEmpty()
            || binding.pinView.text.toString().length < 4) {
            Toast.makeText(mContext, "Enter verification code, please!", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    private fun showProgress(status: Boolean) {
        if (status) {
            loadingDialog!!.showDialog(mContext!!)
        } else {
            loadingDialog!!.dismiss()
        }
    }

    //control
    private fun switchFrag(fragment: Fragment) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onAttach(myContext: Context) {
        super.onAttach(myContext)
        if (activity == null && myContext is LoginActivity) {
            activity = myContext
            this.mContext = myContext
        }
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    companion object {

        @JvmStatic
        fun newInstance(phone_with_intro: String) =
            VerCode().apply {
                this.phone_with_intro = phone_with_intro
            }
    }


}