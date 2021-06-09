package com.oya.doubleq_client.ui.contact_us;

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
import com.oya.doubleq_client.databinding.FragmentContactUsBinding
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.data.api.ApiClient
import com.oya.doubleq_client.data.api.Status

class ContactUs : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentContactUsBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null

    //view model
    private lateinit var viewModel: ContactUsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_us, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions
        binding.backBtn.setOnClickListener(this)
        binding.submitBtn.setOnClickListener(this)
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
        ).get(ContactUsViewModel::class.java)
    }

    private fun postData(title: String, details: String) {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        viewModel.contactUs(title, details, mContext!!).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message)
                        if (it.data.status) {
                            //Do something
                            requireActivity().onBackPressed()
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

    private fun showProgress(status: Boolean) {
        if (status) {
            loadingDialog!!.showDialog(mContext!!)
        } else {
            loadingDialog!!.dismiss()
        }
    }


    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.backBtn -> requireActivity().onBackPressed()
            R.id.submitBtn -> {
                if (isEmpty())
                    return
                TestMsg.show(mContext,SharedPref.getToken(mContext),0)
                postData(binding.titleEt.text.toString(), binding.noteEt.text.toString())
            }
        }
    }

    private fun isEmpty(): Boolean {
        if (binding.titleEt.text.toString().trim() == "") {
            binding.titleEt.error = getString(R.string.required)
            return true
        }
        if (binding.noteEt.text.toString().trim() == "") {
            binding.noteEt.error = getString(R.string.required)
            return true
        }
        return false
    }


    //control
    private fun switchFrag(fragment: Fragment) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onAttach(myContext: Context) {
        super.onAttach(myContext)
        this.mContext = myContext
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactUs().apply {

            }
    }
}