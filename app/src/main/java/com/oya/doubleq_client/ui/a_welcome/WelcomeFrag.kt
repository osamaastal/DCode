package com.oya.doubleq_client.ui.a_welcome

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
import com.bumptech.glide.Glide

import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.ConnectionDetector
import com.oya.doubleq_client.databinding.FragmentWelcomeFragBinding
import com.oya.doubleq_client.pojo.welcome.ConstantItem
import com.oya.doubleq_client.ui.a_welcome.Welcome

class WelcomeFrag( var model: ConstantItem) : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentWelcomeFragBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: Welcome? = null

    //view model
//    private lateinit var viewModel: WelcomeFragViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome_frag, container, false)
        binding = DataBindingUtil.bind(view)!!
        updateUI()
//        init()
        //actions

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(model:ConstantItem) =
            WelcomeFrag(model).apply {

            }
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
    }
    private fun updateUI(){
        binding.titleTv.text = model.name
        binding.detailsTv.text = model.description
        Glide.with(this)
            .load(model.icon)
            .into(binding.img)
    }


    //control
    override fun onAttach(myContext: Context) {
        super.onAttach(myContext)
        if (activity == null && myContext is Welcome) {
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