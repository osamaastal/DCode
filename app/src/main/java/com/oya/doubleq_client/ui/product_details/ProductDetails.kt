package com.oya.doubleq_client.ui.product_details

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.bottom_sheet.BottomSheetSelectColor
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.FragmentProductDetailsBinding
import com.oya.doubleq_client.pojo.search_filter.ProductModelFull
import com.oya.doubleq_client.pojo.sub_category.ColorsModel
import com.oya.doubleq_client.ui.a_other.OtherActivity
import com.oya.doubleq_client.ui.notifications.Notifications
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import java.util.*


class ProductDetails : Fragment(), View.OnClickListener, TagAdapter.EventListener
            , BottomSheetSelectColor.EventListener{

    private lateinit var binding: FragmentProductDetailsBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: OtherActivity? = null
    private lateinit var product_id: String
    private lateinit var tagAdapter: TagAdapter
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var  bottomSheetSelectColor:BottomSheetSelectColor



    //view model
    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()
        getData()
        //actions
        binding.backBtn.setOnClickListener(this)
        binding.notificationBtn.setOnClickListener(this)
        binding.shareBtn.setOnClickListener(this)
        binding.addToCartTv.setOnClickListener(this)
        return view
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)

        tagAdapter = TagAdapter(mContext!!, this)
        colorAdapter = ColorAdapter(mContext!!)
        binding.RVTags.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        binding.RVColors.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        binding.RVTags.adapter = tagAdapter
        binding.RVColors.adapter = colorAdapter
        //slider
        imagesAdapter = ImagesAdapter(mContext)
        binding.imageSlider.setSliderAdapter(imagesAdapter)
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM)
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        binding.imageSlider.startAutoCycle()
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(ProductDetailsViewModel::class.java)
    }

    private fun getData() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        viewModel.getProductDetails(mContext!!, product_id).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        val msg =
                            if (SharedPref.getLanguage(mContext) == cons.AR) it.data!!.messageAr
                            else
                                it.data!!.messageEn
                        HandleErrorMsg.errorMsg(mContext!!, msg, !it.data.status)
                        if (it.data.status) {
                            //Do something
                            updateUI(it.data.items)
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

    private fun updateUI(model: ProductModelFull) {
        binding.productNameTv.text = model.name
        val htmlAsSpanned: Spanned = Html.fromHtml(model.details)
        binding.productDetailsTv.text = htmlAsSpanned
        binding.rateTv.text = String.format(Locale.US, "%.1f", model.rate)
        if (model.discountPrice == 0.0) { //no discount
            binding.totalTv.text = String.format(Locale.US, "%.2f", model.price)
        } else {
            binding.totalTv.text = String.format(Locale.US, "%.2f", model.discountPrice)
            binding.oldTotalTv.text = String.format(Locale.US, "%.2f", model.price)
        }
//        binding.numRatesTv.text = String.format(Locale.US,"%.2f", model.)
        if (model.colors!!.isEmpty()){
            binding.RVColors.visibility = View.GONE
            binding.availableColorsTv.visibility = View.GONE
        }
        if (model.tags!!.isEmpty()){
            binding.RVTags.visibility = View.GONE
            binding.tagsTv.visibility = View.GONE
        }
        colorAdapter.setList(model.colors as ArrayList<ColorsModel>)
        tagAdapter.setList(model.tags as ArrayList<String>)
        imagesAdapter.setList(model.images)
        //bottom sheet
        bottomSheetSelectColor = BottomSheetSelectColor(model.colors, this)
    }

    private fun showProgress(status: Boolean) {
        if (status) {
            loadingDialog!!.showDialog(mContext!!)
        } else {
            loadingDialog!!.dismiss()
        }
    }

    override fun onTagCLick(tag: String) {

    }

    override fun onNextColor(color_id:String) {
        Log.d(TAG, "product_id: $product_id  color_id: $color_id")
        viewModel.addToCart(mContext!!,product_id,1,color_id).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message,!it.data.status)
                        if (it.data.status) {
                            //Do something
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
            R.id.notificationBtn -> switchFrag(Notifications())
            R.id.addToCartTv -> {
                bottomSheetSelectColor.show(
                    parentFragmentManager,
                    "ModalBottomSheet")
            }
            R.id.shareBtn -> Toast.makeText(mContext, "Working on it!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "ProductDetails"
        @JvmStatic
        fun newInstance(product_id: String) =
            ProductDetails().apply {
                this.product_id = product_id
            }
    }

    private fun switchFrag(fragment: Fragment) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}