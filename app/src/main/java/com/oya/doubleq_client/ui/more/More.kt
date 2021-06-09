package com.oya.doubleq_client.ui.more;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.FragmentMoreBinding
import com.oya.doubleq_client.pojo.user.User
import com.oya.doubleq_client.pojo.user.UserLocal
import com.oya.doubleq_client.ui.a_login.LoginActivity
import com.oya.doubleq_client.ui.a_main.MainActivity
import com.oya.doubleq_client.ui.a_other.OtherActivity

class More : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMoreBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: MainActivity? = null

    //view model
    private lateinit var viewModel: MoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions
        binding.editProfileLayout.setOnClickListener(this)
        binding.aboutUsTv.setOnClickListener(this)
        binding.contactUsTv.setOnClickListener(this)
        binding.warrantiesTv.setOnClickListener(this)
        binding.favoritesTv.setOnClickListener(this)
        binding.faqTv.setOnClickListener(this)
        binding.notificationsTv.setOnClickListener(this)
        binding.logoutTv.setOnClickListener(this)
        return view
    }

    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        if (SharedPref.getUserData(mContext).isGuest){
            binding.favoritesTv.visibility = View.GONE
            binding.warrantiesTv.visibility = View.GONE
            binding.editProfileLayout.visibility = View.GONE
            binding.notificationsTv.visibility = View.GONE
            binding.contactUsTv.visibility = View.GONE
            binding.logoutTv.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        val user = SharedPref.getUserData(mContext)
        binding.userNameTv.text = user.fullName
        binding.phoneTv.text = user.phoneNumber
        Glide.with(this)
            .load(user.image)
            .error(R.drawable.profile_test)
            .into(binding.userImg)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.backBtn -> requireActivity().onBackPressed()
            R.id.editProfileLayout -> {
                MainActivity.goToOtherActivity("Profile")
//                navigateFrag(MoreDirections.actionMoreToProfile(),v)
            }
            R.id.aboutUsTv -> {
                MainActivity.goToOtherActivity("Policy", cons.ABOUT)
//                navigateFrag(MoreDirections.actionMoreToAboutUs(),v)
            }
            R.id.contactUsTv -> {
                MainActivity.goToOtherActivity("ContactUs")
//                navigateFrag(MoreDirections.actionMoreToContactUs(),v)
            }
            R.id.warrantiesTv -> {
                MainActivity.goToOtherActivity("Warranties")
//                navigateFrag(MoreDirections.actionMoreToWarranties(),v)
            }
            R.id.favoritesTv -> {
                MainActivity.goToOtherActivity("Favorites",getString(R.string.favorites))
//                navigateFrag(MoreDirections.actionMoreToFavorites(),v)
            }
            R.id.faqTv -> {
                MainActivity.goToOtherActivity("FAQ")
//                navigateFrag(MoreDirections.actionMoreToFAQ(),v)
            }
            R.id.notificationsTv -> {
                MainActivity.goToOtherActivity("Notifications")
//                navigateFrag(MoreDirections.actionMoreToNotifications(),v)
            }
            R.id.logoutTv -> {
                logout()
            }
        }
    }

    private fun navigateFrag(navDirections: NavDirections, v: View) {
        Navigation.findNavController(v).navigate(navDirections)
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(MoreViewModel::class.java)
    }

    private fun logout() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        viewModel.logout(mContext!!).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message,!it.data.status)
                        if (it.data.status) {
                            //Do something
                            SharedPref.setId(mContext, "")
                            val intent = Intent(mContext,LoginActivity::class.java)
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

    private fun showProgress(status: Boolean) {
        if (status) {
            loadingDialog!!.showDialog(mContext!!)
        } else {
            loadingDialog!!.dismiss()
        }
    }


    private fun switchFrag(fragment: Fragment) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    //control
    override fun onAttach(myContext: Context) {
        super.onAttach(myContext)
        if (activity == null && myContext is MainActivity) {
            activity = myContext
        }
        this.mContext = myContext
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            More().apply {

            }
    }

}