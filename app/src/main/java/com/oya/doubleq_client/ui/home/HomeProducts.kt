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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.FragmentHomeProductsBinding
import com.oya.doubleq_client.pojo.category.CategoryModel
import com.oya.doubleq_client.pojo.home.AdvsModel
import com.oya.doubleq_client.pojo.home.HomeModel
import com.oya.doubleq_client.pojo.product.ProductModel
import com.oya.doubleq_client.ui.a_main.MainActivity
import com.oya.doubleq_client.ui.products.ProductAdapter
import com.oya.doubleq_client.ui.products.ProductViewModel
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations

class HomeProducts : Fragment(), View.OnClickListener, ProductAdapter.EventListener {

    private lateinit var binding: FragmentHomeProductsBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: MainActivity? = null
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productAdapterRecommended: ProductAdapter
    private lateinit var catsAdapter: CatTextAdapter
    private lateinit var sliderAdapter: SliderAdapter

    //view model
    private lateinit var viewModel: HomeViewModel
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_products, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()

        //actions
        binding.moreAccessoriesTv.setOnClickListener(this)
        binding.moreRecommendedTv.setOnClickListener(this)
        return view
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
        //Dummy Adapter
        productAdapter = ProductAdapter(mContext!!, this, cons.VIEW_TYPE_GRID)
        productAdapterRecommended = ProductAdapter(mContext!!, this, cons.VIEW_TYPE_HORIZONTAL)
        catsAdapter = CatTextAdapter(mContext!!)
        binding.RVProducts.layoutManager = GridLayoutManager(mContext, 2)
        binding.RVProductsRecommended.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        binding.RVCats.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)

        binding.RVProducts.adapter = productAdapter
        binding.RVProductsRecommended.adapter = productAdapterRecommended
        binding.RVCats.adapter = catsAdapter

        //slider
        sliderAdapter = SliderAdapter(mContext)
        binding.imageSlider.setSliderAdapter(sliderAdapter)
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM)
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        binding.imageSlider.startAutoCycle()
    }

    private fun setUpViewModel() {
        productViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(ProductViewModel::class.java)

        viewModel.mutableLiveDataHomeModel.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                productAdapterRecommended.setList(it.recomended as ArrayList<ProductModel>)
                catsAdapter.setList(it.categories as ArrayList<CategoryModel>)
                sliderAdapter.setList(it.advs)
            }
        })
//        val homeModel = viewModel.mutableLiveDataHomeModel.value
//        if (homeModel!=null){
//            TestMsg.show(mContext,"ads size"+homeModel.advs!!.size,0)
//            productAdapterRecommended.setList(homeModel.recomended as ArrayList<ProductModel>)
//            sliderAdapter.setList(homeModel.advs)
//            catsAdapter.setList(homeModel.categories as ArrayList<CategoryModel>)
//        }else{
//            TestMsg.show(mContext,"mutableLiveDataHomeModel = null ",0)
//        }

    }

    override fun onLike(product_id: String, itemViewType: Int) {
        productViewModel.toggleFavorite(mContext!!, product_id).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        if (SharedPref.getLanguage(mContext) == cons.AR)
                            HandleErrorMsg.errorMsg(mContext!!, it.data!!.messageAr, false)
                        else
                            HandleErrorMsg.errorMsg(mContext!!, it.data!!.messageEn, false)
                        if (it.data.status) {
                            //Do something
                            if (itemViewType == cons.VIEW_TYPE_HORIZONTAL)// recommended
                                productAdapterRecommended.toggleIcon(it.data.items.productId)
                            else// accessories
                                productAdapter.toggleIcon(it.data.items.productId)
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

    override fun onProductDetails(product_id: String) {
        MainActivity.goToOtherActivity("ProductDetails", product_id)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.backBtn -> requireActivity().onBackPressed()
            R.id.moreAccessoriesTv -> {
                MainActivity.goToOtherActivity("Favorites", getString(R.string.accessories))
            }
            R.id.moreRecommendedTv -> {
                MainActivity.goToOtherActivity("Favorites", getString(R.string.recommended_for_you))
            }
        }
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
        if (activity == null && myContext is MainActivity) {
            activity = myContext
            this.mContext = myContext
        }
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }


    companion object {
        @JvmStatic
        fun newInstance(homeViewModel: HomeViewModel) =
            HomeProducts().apply {
                viewModel = homeViewModel
            }
    }
}