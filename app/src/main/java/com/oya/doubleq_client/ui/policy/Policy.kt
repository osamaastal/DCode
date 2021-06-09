package com.oya.doubleq_client.ui.policy;

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.FragmentPolicyBinding

class Policy : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentPolicyBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private lateinit var type:String
    //view model
    private lateinit var viewModel: StaticPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_policy, container, false)
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
        fun newInstance(type_: String) =
            Policy().apply {
                type = type_
            }
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
        ).get(StaticPageViewModel::class.java)
    }

    private fun getData() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        //write network (live-template)
        viewModel.getPageData(type, mContext!!).observe(this as LifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)//if status = true don't show
                        if (it.data.status) {
                            //Do something
//                            webView(it.data.constant.content)
                            val htmlAsSpanned: Spanned = Html.fromHtml(it.data.constant.content)
                            binding.detailsTv.text = htmlAsSpanned
                            binding.titleTv.text = it.data.constant.name

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
//    private fun webView(body: String) {
//        binding.webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null)
//    }

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