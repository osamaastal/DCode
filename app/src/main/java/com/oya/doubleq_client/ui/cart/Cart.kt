package com.oya.doubleq_client.ui.cart;

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
import androidx.recyclerview.widget.LinearLayoutManager

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.DummyAdapter
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.databinding.FragmentCartBinding
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.ui.a_main.MainActivity

class Cart : Fragment(), View.OnClickListener, CartAdapter.EventListener {

    private lateinit var binding: FragmentCartBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: MainActivity? = null

    //view model
//    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Cart().apply {

            }
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        //Dummy Adapter
        val adapter = CartAdapter(mContext!!, this)
        //dummy data
        val list = arrayListOf<Any>()
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        binding.RVCart.layoutManager = LinearLayoutManager(mContext)
        binding.RVCart.adapter = adapter
        adapter.setList(list)
    }

    private fun setUpViewModel() {
        //view model
//        viewModel = ViewModelProviders.of(
//            this,
//            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
//        ).get(CartViewModel::class.java)
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
    override fun onRemoveItem(position: Int) {

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

        }
    }

    private fun switchFrag(fragment: Fragment) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


}