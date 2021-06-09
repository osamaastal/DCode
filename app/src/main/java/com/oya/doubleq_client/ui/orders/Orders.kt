package com.oya.doubleq_client.ui.orders;

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
import com.oya.doubleq_client.databinding.FragmentOrdersBinding
import com.oya.doubleq_client.ui.a_main.MainActivity
import com.oya.doubleq_client.ui.warranties.WarrantyProductAdapter
import com.oya.doubleq_client.ui.warranties.WarrantyProductDateAdapter

class Orders : Fragment(), View.OnClickListener,WarrantyProductAdapter.EventListener {

    private lateinit var binding: FragmentOrdersBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: MainActivity? = null

    private lateinit var orderDataAdapter: OrderDateAdapter
    private lateinit var warrantyProductDateAdapter: WarrantyProductDateAdapter
    //view model
//    private lateinit var viewModel: OrdersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions
        binding.productsTap.setOnClickListener(this)
        binding.ordersTap.setOnClickListener(this)
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Orders().apply {

            }
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        //Dummy Adapter
        orderDataAdapter = OrderDateAdapter(mContext!!)
        warrantyProductDateAdapter = WarrantyProductDateAdapter(mContext!!, this)
        binding.RVOrders.layoutManager = LinearLayoutManager(mContext)
        binding.RVOrders.adapter = orderDataAdapter
        //dummy data
        val list = arrayListOf<Any>()
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        orderDataAdapter.setList(list)
        warrantyProductDateAdapter.setList(list)
    }

    private fun setUpViewModel() {
        //view model
//        viewModel = ViewModelProviders.of(
//            this,
//            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
//        ).get(OrdersViewModel::class.java)
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
    override fun onDetails() {
        MainActivity.goToOtherActivity("OrderDetailsWarranty_Product")
    }

    //control
    override fun onAttach(myContext: Context) {
        super.onAttach(myContext)
        if (activity == null && myContext is MainActivity) {
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
            R.id.productsTap -> {
                binding.productsTap.setBackgroundResource(R.drawable.bg_req_white_r16)
                binding.ordersTap.setBackgroundResource(R.color.trans)
                binding.RVOrders.adapter = warrantyProductDateAdapter
            }
            R.id.ordersTap -> {
                binding.ordersTap.setBackgroundResource(R.drawable.bg_req_white_r16)
                binding.productsTap.setBackgroundResource(R.color.trans)
                binding.RVOrders.adapter = orderDataAdapter
            }
        }
    }

    private fun switchFrag(fragment: Fragment) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


}