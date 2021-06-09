package com.oya.doubleq_client.ui.login

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
import com.google.firebase.messaging.FirebaseMessaging
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.data.api.HandleErrorMsg

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.databinding.FragmentLoginBinding
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.ui.a_main.MainActivity
import com.oya.doubleq_client.ui.policy.Policy
import com.oya.doubleq_client.ui.ver_code.VerCode
import com.oya.doubleq_client.data.api.ApiClient
import com.oya.doubleq_client.data.api.Status
import kotlinx.android.synthetic.main.view_phone_et.view.*

class Login : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLoginBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null

    //view model
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions
        binding.signInBtn.setOnClickListener(this)
        binding.termsTv.setOnClickListener(this)
        binding.policyTv.setOnClickListener(this)
        binding.guestLoginTv.setOnClickListener(this)
        return view
    }




    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(LoginViewModel::class.java)
    }

    private fun guestLogin() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        viewModel.guestLogin().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message,!it.data.status)
                        if (it.data.status) {
                            //Do something
                            SharedPref.setGuestToken(it.data.guest,mContext)
                            val intent = Intent(mContext,MainActivity::class.java)
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
    private fun login() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        if(!binding.agreementCheckBox.isChecked) {
            Toast.makeText(mContext, getString(R.string.accept_agreement),Toast.LENGTH_SHORT).show()
            return
        }
        val phone_with_intro = cons.country_code + binding.includePhone.phoneEt.text.toString()
        val fcmToken = FirebaseMessaging.getInstance().token.result
        viewModel.login(phone_with_intro, fcmToken, mContext!!).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message,!it.data.status)
                        if (it.data.status) {
                            //Do something
                            TestMsg.show(mContext,""+it.data.user.verifyCode,0)
                            SharedPref.setUserData(it.data.user,mContext)
                            switchFrag(VerCode.newInstance(phone_with_intro))
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
            R.id.signInBtn -> {
                if (isEmpty())
                    return
                login()
            }
            R.id.termsTv -> {
                switchFrag(Policy.newInstance(cons.TERMS))
            }
            R.id.policyTv -> {
                switchFrag(Policy.newInstance(cons.PRIVACY))
            }
            R.id.guestLoginTv -> {
                guestLogin()
            }
        }
    }

    private fun isEmpty():Boolean{
        val phoneEt = binding.includePhone.phoneEt
        if(phoneEt.text.isNullOrEmpty()){
            phoneEt.error = getString(R.string.required)
            return true
        }
        if (!phoneEt.text.startsWith("5")){
            phoneEt.error = "Must start by 5"
            return true
        }
        if (phoneEt.text.length < 9) {
            phoneEt.error = getString(R.string.not_valid_phone)
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
            .addToBackStack(null)
            .commit()
    }
    override fun onAttach(myContext: Context) {
        super.onAttach(myContext)
        this.mContext = myContext
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login().apply {

            }
    }
}