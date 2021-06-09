package com.oya.doubleq_client.ui.home;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.bumptech.glide.Glide

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.FragmentHomeBinding
import com.oya.doubleq_client.ui.a_main.MainActivity

class Home : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: MainActivity? = null
    private var navController: NavController? = null
    private lateinit var v: View
    private lateinit var homeProducts: HomeProducts
    private lateinit var homeRepair: HomeRepair

    //view model
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        binding = DataBindingUtil.bind(v)!!
        init()
        setUpViewModel()
//        if (!isRecreated) {
        getData()
//        }
        //actions
        binding.repairTap.setOnClickListener(this)
        binding.productsTap.setOnClickListener(this)
        binding.searchBtn.setOnClickListener(this)
        binding.userImg.setOnClickListener(this)
        return v
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        init()
//        setUpViewModel()
//        getData()
//    }

    companion object {
        private var isRecreated = false

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
            }
    }

    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        if (SharedPref.getUserData(mContext).isGuest)
            binding.userImg.visibility = View.GONE
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(HomeViewModel::class.java)

        homeProducts = HomeProducts.newInstance(viewModel)
        homeRepair = HomeRepair.newInstance(viewModel)
        switchFrag(homeRepair)
    }

    private fun getData() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        //write network (live-template)
        viewModel.getHome(mContext!!).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)
                        if (it.data.status) {
                            //Do something
                            viewModel.updateModel(it.data.items)
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
    override fun onResume() {
        super.onResume()
        val user = SharedPref.getUserData(mContext)
        Glide.with(this)
            .load(user.image)
            .error(R.drawable.profile_test)
            .into(binding.userImg)

    }

    override fun onPause() {
        super.onPause()
        isRecreated = true
    }

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
            R.id.searchBtn -> MainActivity.goToOtherActivity("Search")
            R.id.productsTap -> {
                binding.productsTap.setBackgroundResource(R.drawable.bg_req_white_r16)
                binding.repairTap.setBackgroundResource(R.color.trans)
                switchFrag(homeProducts)
            }
            R.id.repairTap -> {
                binding.repairTap.setBackgroundResource(R.drawable.bg_req_white_r16)
                binding.productsTap.setBackgroundResource(R.color.trans)
                switchFrag(homeRepair)
            }
            R.id.userImg -> {
                MainActivity.goToOtherActivity("Profile")
            }
        }
    }

    private fun switchFrag(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
//    private fun onNavigate(id: Int) {
//        navController!!.navigateUp() // to clear previous navigation history
//        navController!!.navigate(id)
//    }

}