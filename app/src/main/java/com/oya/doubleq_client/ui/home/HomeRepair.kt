package com.oya.doubleq_client.ui.home;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.databinding.FragmentHomeRepairBinding
import com.oya.doubleq_client.pojo.category.CategoryModel
import com.oya.doubleq_client.pojo.product.ProductModel
import com.oya.doubleq_client.pojo.types.TypeModel
import com.oya.doubleq_client.ui.a_main.MainActivity

class HomeRepair : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeRepairBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: MainActivity? = null
    private lateinit var typeAdapter:TypeAdapter

    //view model
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_repair, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(homeViewModel: HomeViewModel) =
            HomeRepair().apply {
                viewModel = homeViewModel
            }
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        //Dummy Adapter
        typeAdapter = TypeAdapter(mContext!!)
        binding.RVRepair.layoutManager = LinearLayoutManager(mContext)
        binding.RVRepair.adapter = typeAdapter
    }

    private fun setUpViewModel() {
        viewModel.mutableLiveDataHomeModel.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                typeAdapter.setList(it.types as ArrayList<TypeModel>)
            }
        })
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
}