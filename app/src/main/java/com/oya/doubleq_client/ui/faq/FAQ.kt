package com.oya.doubleq_client.ui.faq;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.oya.doubleq_client.data.api.ApiService
import com.oya.doubleq_client.data.api.HandleErrorMsg

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.DummyAdapter
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.FragmentFaqBinding
import com.oya.doubleq_client.pojo.faq.ConstantItem
import com.oya.doubleq_client.ui.a_other.OtherActivity
import com.oya.doubleq_client.data.api.ApiClient
import com.oya.doubleq_client.data.api.Status
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

class FAQ : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentFaqBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: OtherActivity? = null
    private lateinit var adapter: FaqAdapter

    //view model
    private lateinit var viewModel: FAQViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faq, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()
        getData()

        //actions
        binding.backBtn.setOnClickListener(this)
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FAQ().apply {

            }
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        //Adapter
        adapter = FaqAdapter(mContext!!)
        val alphaAdapter = ScaleInAnimationAdapter(adapter)// animate adapter
        alphaAdapter.setDuration(300)
        binding.RV.layoutManager = LinearLayoutManager(mContext)
        binding.RV.adapter = adapter
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(FAQViewModel::class.java)
    }

    private fun getData() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        //write network
        viewModel.getData(mContext!!).observe(this as LifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)
                        if (it.data.status) {
                            //Do something
                            adapter.setList(it.data.constant)
                        } else{
                            TestMsg.show(mContext,"status: "+it.data.status, 0)
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


    //control
    override fun onAttach(myContext: Context) {
        super.onAttach(myContext)
        this.mContext = myContext
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.backBtn -> requireActivity().onBackPressed()

        }
    }

    private fun switchFrag(fragment: Fragment) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}