package com.oya.doubleq_client.ui.notifications;

import android.content.Context
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
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.classes.pagenation_scroll.RecyclerViewLoadMoreScroll
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.FragmentNotificationsBinding
import com.oya.doubleq_client.pojo.notifications.NotificationsItem
import com.oya.doubleq_client.ui.a_other.OtherActivity
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter

class Notifications : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNotificationsBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: OtherActivity? = null
    private var alphaAdapter: AlphaInAnimationAdapter? = null
    private lateinit var adapter: NotificationAdapter
    private lateinit var mItems: ArrayList<NotificationsItem>

    //view model
    private lateinit var viewModel: NotificationsViewModel
    private var page = 0
    private var lastPage = 0
    private var limit = 10
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()
        getData()
        //actions
        binding.backBtn.setOnClickListener(this)
        binding.swipeRefresh.setOnRefreshListener {
            mItems.clear()
            adapter.notifyDataSetChanged()
            page = 0
            getData()
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Notifications().apply {

            }
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        // Adapter
        mItems = arrayListOf()
        adapter = NotificationAdapter(mContext!!)
        alphaAdapter = AlphaInAnimationAdapter(adapter)// animate adapter
        alphaAdapter!!.setDuration(1000)
        binding.RV.layoutManager = LinearLayoutManager(mContext)
        binding.RV.adapter = adapter
        setScrollListener()
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(NotificationsViewModel::class.java)
    }

    private fun getData() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return

        viewModel.getData(page, limit, mContext!!).observe(this as LifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.swipeRefresh.isRefreshing = false
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)
                        if (it.data.status) {
                            //Do something
                            lastPage = it.data.pagination.totalPages
                            if (scrollListener != null) {
                                scrollListener!!.setLoaded()
                            }
                            if (it.data.notifications != null) {
                                if (page == 0) {
                                    mItems.clear()
                                    if (it.data.notifications.isEmpty()) {
                                        binding.linearNoData.visibility = View.VISIBLE
                                    } else {
                                        binding.linearNoData.visibility = View.GONE
                                    }
                                }
                                mItems.addAll(it.data.notifications)
                                adapter.setList(mItems)
                            } else {
                                if (page == 1)
                                    binding.linearNoData.visibility = View.VISIBLE
                                else
                                    binding.linearNoData.visibility = View.GONE
                            }
                        }
                    }
                    Status.ERROR -> {//request error
                        binding.swipeRefresh.isRefreshing = false
                        HandleErrorMsg.errorMsg(mContext!!, it.message!!)
                    }
                    Status.LOADING -> {
                        binding.swipeRefresh.isRefreshing = true
                    }
                }
            }
        })
    }

    /*********************Scroll  ***************/
    private var scrollListener: RecyclerViewLoadMoreScroll? = null

    private fun setScrollListener() {
        scrollListener = RecyclerViewLoadMoreScroll(binding.RV.layoutManager as LinearLayoutManager)
        scrollListener!!.setOnLoadMoreListener {
            if (page < lastPage) {
                page++
                getData()
            } else {
                Toast.makeText(context, R.string.no_more, Toast.LENGTH_SHORT).show()
            }
        }
        binding.RV.addOnScrollListener(scrollListener!!)
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