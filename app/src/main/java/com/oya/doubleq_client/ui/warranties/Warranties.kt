package com.oya.doubleq_client.ui.warranties;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.DummyAdapter
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.databinding.FragmentWarrantiesBinding
import com.oya.doubleq_client.ui.a_other.OtherActivity

class Warranties : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentWarrantiesBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: OtherActivity? = null

    private lateinit var dummyAdapterOrders: DummyAdapter
    private lateinit var dummyAdapterProduct: DummyAdapter

    //view model
//    private lateinit var viewModel: WarrantiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_warranties, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions
        binding.backBtn.setOnClickListener(this)
        binding.productsTap.setOnClickListener(this)
        binding.ordersTap.setOnClickListener(this)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Warranties().apply {

            }
    }

    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        //Dummy Adapter
        dummyAdapterOrders = DummyAdapter(mContext, R.layout.row_warranty_order, 5)
        dummyAdapterProduct = DummyAdapter(mContext, R.layout.row_warranty_product_date, 5)
        binding.RVOrders.layoutManager = LinearLayoutManager(mContext)
        binding.RVOrders.adapter = dummyAdapterOrders
    }

    private fun setUpViewModel() {
        //view model
//        viewModel = ViewModelProviders.of(
//            this,
//            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
//        ).get(WarrantiesViewModel::class.java)
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
            R.id.productsTap ->{
                binding.productsTap.setBackgroundResource(R.drawable.bg_req_white_r16)
                binding.ordersTap.setBackgroundResource(R.color.trans)
                binding.RVOrders.adapter = dummyAdapterProduct
            }
            R.id.ordersTap ->{
                binding.ordersTap.setBackgroundResource(R.drawable.bg_req_white_r16)
                binding.productsTap.setBackgroundResource(R.color.trans)
                binding.RVOrders.adapter = dummyAdapterOrders
            }
        }
    }

    private fun switchFrag(fragment: Fragment) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}