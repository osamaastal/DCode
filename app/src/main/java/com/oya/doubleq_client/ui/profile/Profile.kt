package com.oya.doubleq_client.ui.profile;

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.chaos.view.PinView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.*
import com.oya.doubleq_client.classes.dialog.LoadingDialog
import com.oya.doubleq_client.data.api.*
import com.oya.doubleq_client.data.api.base.ViewModelFactory
import com.oya.doubleq_client.databinding.FragmentProfileBinding
import com.oya.doubleq_client.pojo.user.User
import com.oya.doubleq_client.ui.a_other.OtherActivity
import com.oya.doubleq_client.ui.ver_code.VerCodeViewModel
import kotlinx.android.synthetic.main.view_phone_et.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class Profile : Fragment(), View.OnClickListener, MyPermissionManager.MyPermissionListener {

    lateinit var binding: FragmentProfileBinding
    private var connectionDetector: ConnectionDetector? = null
    private var loadingDialog: LoadingDialog? = null
    private var mContext: Context? = null
    private var activity: OtherActivity? = null
    private lateinit var user: User
    var isImageChanged = false
    private val TAG = "Profile"
    val MAX_SIZE = 800
    var image: MultipartBody.Part? = null

    //view model
    private lateinit var viewModel: ProfileViewModel
    private lateinit var verCodeViewModel: VerCodeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = DataBindingUtil.bind(view)!!
        init()
        setUpViewModel()
        getData()
        //actions
        binding.backBtn.setOnClickListener(this)
        binding.updateBtn.setOnClickListener(this)
        binding.userImg.setOnClickListener(this)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {

            }
    }


    private fun init() {
        loadingDialog = LoadingDialog()
        connectionDetector = ConnectionDetector(context)
    }

    private fun setUpViewModel() {
        //view model
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(ProfileViewModel::class.java)

        verCodeViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(VerCodeViewModel::class.java)
    }

    private fun getData() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return

        viewModel.getData(mContext!!).observe(this as LifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)
                        if (it.data.status) {
                            //Do something
                            user = it.data.user
                            updateUI(user)
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

    private fun updateProfile() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return
        val name = binding.fullNameEt.text.toString()
        val phone = cons.country_code + binding.includePhone.phoneEt.text.toString()
        viewModel.updateProfile(name, phone,image, mContext!!).observe(this as LifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)
                        if (it.data.status) {
                            //Do something
                            if(phone!=user.phoneNumber){
                                TestMsg.show(mContext, ""+it.data.user.verifyCode,0)
                                //show sheet
                                binding.bottomSheetVerCOde.visibility = View.VISIBLE
                                bottomSheetBehavior =
                                    BottomSheetBehavior.from<View>(binding.bottomSheetVerCOde)
                                bottomSheetBehavior!!.isDraggable = false
                                showBottomSheet(false)
                                binding.includePhone.phoneEt.isEnabled = false
                                binding.bottomSheetVerCOde.findViewById<Button>(R.id.verifyBtn).setOnClickListener(this)
                            }else{
                                SharedPref.setUserData(it.data.user, mContext)
                                requireActivity().onBackPressed()
                            }

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
    private fun verCode() {
        if (!connectionDetector!!.isConnectingToInternet(mContext)) return

        val phone = cons.country_code + binding.includePhone.phoneEt.text.toString()
        val code = binding.bottomSheetVerCOde.findViewById<PinView>(R.id.pinView).text.toString()
        verCodeViewModel.verCode(phone, code, mContext!!)
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            showProgress(false)
                            HandleErrorMsg.errorMsg(mContext!!, it.data!!.message, !it.data.status)
                            if (it.data.status) {
                                //Do something
                                SharedPref.setUserData(it.data.user, mContext)
                                requireActivity().onBackPressed()
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

    private fun updateUI(user: User) {
        binding.fullNameEt.setText(user.fullName)
        binding.fullNameTv.text = user.fullName
        binding.includePhone.phoneEt.setText(
            user.phoneNumber.substring(3)
        )
        Glide.with(this)
            .load(user.image)
            .error(R.drawable.profile_test)
            .into(binding.userImg)
    }

    private fun showProgress(status: Boolean) {
        if (status) {
            loadingDialog!!.showDialog(mContext!!)
        } else {
            loadingDialog!!.dismiss()
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.backBtn -> requireActivity().onBackPressed()
            R.id.updateBtn -> {
                if (isEmpty())
                    return
                if (!isDataChanged()) {
                    Toast.makeText(mContext, getString(R.string.no_changes), Toast.LENGTH_SHORT)
                        .show()
                    return
                }
                updateProfile()
            }
            R.id.userImg -> {
                PERMISSION_CODE = cons.PERMISSION_GALLERY
                checkGalleryPermission()
            }
            R.id.verifyBtn ->{
                val pinView = binding.bottomSheetVerCOde.findViewById<PinView>(R.id.pinView)
                if ( pinView.text.isNullOrEmpty()||
                        pinView.text.toString().length<4){
                    Toast.makeText(mContext,getString(R.string.enter_ver_code), Toast.LENGTH_SHORT).show()
                    return
                }
                verCode()
            }

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



    private fun isEmpty(): Boolean {
        if (binding.fullNameEt.text.isNullOrEmpty()) {
            binding.fullNameEt.error = getString(R.string.required)
            return true
        }
        val phoneEt = binding.includePhone.phoneEt
        if (phoneEt.text.isNullOrEmpty()) {
            phoneEt.error = getString(R.string.required)
            return true
        }
        if (!phoneEt.text.startsWith("5")) {
            phoneEt.error = getString(R.string.must_start_by_5)
            return true
        }
        if (phoneEt.text.length < 9) {
            phoneEt.error = getString(R.string.not_valid_phone)
            return true
        }
        return false
    }

    private fun isDataChanged(): Boolean {
        if (binding.fullNameEt.text.toString() != user.fullName)
            return true
        val phone = cons.country_code + binding.includePhone.phoneEt.text.toString()
        if (phone != user.phoneNumber)
            return true
        if (isImageChanged)
            return true
        return false
    }

    //bottom sheet behavior
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null

    private fun showBottomSheet(isToggle: Boolean) {
        if (isToggle) {
            if (bottomSheetBehavior!!.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                bottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        } else {
            if (bottomSheetBehavior!!.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    /**************************Permission  */
    var PERMISSION_CODE: Int? = null
    private fun checkGalleryPermission() {
        val myPermissionManager = MyPermissionManager()
        myPermissionManager.checkGalleryPermission(context, this)
    }

    override fun onGranted() {
        if (PERMISSION_CODE == cons.PERMISSION_GALLERY) catchFrom_GALLERY(USER_IMAGE)
    }

    override fun onDenied() {
        val parentLayout = binding.root
        var msg = ""
        if (PERMISSION_CODE == cons.PERMISSION_GALLERY) {
            msg = getString(R.string.accessGalleryIdNeeded)
        }
        val snackbar = Snackbar
            .make(parentLayout, msg, Snackbar.LENGTH_LONG)
            .setAction(R.string.allow) { view -> checkGalleryPermission() }
        snackbar.show()
    }

    //TODO *******************Choose Images***********************************
    private val USER_IMAGE = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }
        if (requestCode == USER_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val contentURI = data.data
                try {
                    if (contentURI == null) {
                        Toast.makeText(context, "contentURI = null", Toast.LENGTH_SHORT).show()
                        return
                    }
                    val fileBig =
                        File(MyImageManager.getRealPathFromURI(context, contentURI))
                    val filePath: String = fileBig.path
                    MyAsyncTask(filePath).execute()
                } catch (e: Exception) {
                    Toast.makeText(context, "Failed! > " + e.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    fun catchFrom_GALLERY(CODE: Int) {
        try {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, CODE)
        } catch (e: Exception) {
            Log.e(TAG, "" + e.message)
        }
    }

    inner class MyAsyncTask(private val filePath: String) :
        AsyncTask<Void?, Void?, Bitmap>() {

        override fun doInBackground(vararg params: Void?): Bitmap {
            return BitmapFactory.decodeFile(filePath)
        }

        override fun onPostExecute(bitmapBig: Bitmap) {
            try {
                val bitmapSmall = MyImageManager.getResizedBitmap(bitmapBig, MAX_SIZE)
                val fileSmall = MyImageManager.getFileFromBitmap(bitmapSmall)
                binding.userImg.setImageBitmap(bitmapSmall)
                val requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), fileSmall)
                // MultipartBody.Part is used to send also the actual file name
                image = MultipartBody.Part.createFormData("image", fileSmall.name, requestFile)
                isImageChanged = true
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "" + e.message)
                Toast.makeText(context, "" + e.message, Toast.LENGTH_SHORT).show()
            }
        }

        init {
            Log.d(TAG, "filePath: $filePath")
        }
    }

}