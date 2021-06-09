package com.oya.doubleq_client.ui.device_to_repair;

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.DummyAdapter
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.databinding.FragmentDeviceToRepairBinding
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.ui.a_other.OtherActivity
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter

class DeviceToRepair : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentDeviceToRepairBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: OtherActivity? = null

    //view model
//    private lateinit var viewModel: DeviceToRepairViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_device_to_repair, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions
        binding.backBtn.setOnClickListener(this)
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeviceToRepair().apply {

            }
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        //Dummy Adapter
        val dummyAdapterProducts = DummyAdapter(mContext, R.layout.row_device, 10)
        val dummyAdapterCats = DummyAdapter(mContext, R.layout.row_cats_text, 10)
        val alphaAdapter = AlphaInAnimationAdapter(dummyAdapterProducts)// animate adapter
        alphaAdapter.setDuration(1000)
        binding.RVProducts.layoutManager = GridLayoutManager(mContext,2)
        binding.RVCats.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)
        binding.RVProducts.adapter = alphaAdapter
        binding.RVCats.adapter = dummyAdapterCats
    }

    private fun setUpViewModel() {
        //view model
//        viewModel = ViewModelProviders.of(
//            this,
//            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
//        ).get(DeviceToRepairViewModel::class.java)
    }

    private fun postData() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        //write network (live-template)
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
        if (activity == null && myContext is OtherActivity) {
            activity = myContext
            this.mContext = myContext
        }
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