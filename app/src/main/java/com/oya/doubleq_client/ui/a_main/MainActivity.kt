package com.oya.doubleq_client.ui.a_main

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.oya.doubleq_client.R
import com.oya.doubleq_client.databinding.ActivityMainBinding
import com.oya.doubleq_client.ui.a_other.OtherActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
        //actions
        binding!!.layHome.setOnClickListener(this)
        binding!!.layCart.setOnClickListener(this)
        binding!!.layOrders.setOnClickListener(this)
        binding!!.layMore.setOnClickListener(this)
        binding!!.mainBtn.setOnClickListener(this)
    }

    private fun init() {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
//        navController = navHostFragment.navController
        mContext = this
        navController = findNavController(R.id.navHostFragment)
        resetBottomNav(binding!!.homeIv)
        onItemSelected(R.id.home2)
    }

    private fun resetBottomNav(img: ImageView) {
        binding!!.homeIv.setColorFilter(
            ContextCompat.getColor(this, R.color.gray90),
            PorterDuff.Mode.MULTIPLY
        )
        binding!!.cartIv.setColorFilter(
            ContextCompat.getColor(this, R.color.gray90),
            PorterDuff.Mode.MULTIPLY
        )
        binding!!.orderIv.setColorFilter(
            ContextCompat.getColor(this, R.color.gray90),
            PorterDuff.Mode.MULTIPLY
        )
        binding!!.moreIv.setColorFilter(
            ContextCompat.getColor(this, R.color.gray90),
            PorterDuff.Mode.MULTIPLY
        )
        //
        img.setColorFilter(
            ContextCompat.getColor(this, R.color.main_color),
            PorterDuff.Mode.MULTIPLY
        )
    }

    private fun onItemSelected(id: Int) {
        navController!!.navigateUp() // to clear previous navigation history
        navController!!.navigate(id)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.layHome -> {
                resetBottomNav(binding!!.homeIv)
//                findNavController(Home()).navigate(R.id.nav.home2)
                onItemSelected(R.id.home2)
            }
            R.id.layCart -> {
                resetBottomNav(binding!!.cartIv)
//                findNavController(Cart()).navigate(R.id.cart)
                onItemSelected(R.id.cart)
            }
            R.id.layOrders -> {
                resetBottomNav(binding!!.orderIv)
//                findNavController(Orders()).navigate(R.id.orders)
                onItemSelected(R.id.orders)
            }
            R.id.layMore -> {
                resetBottomNav(binding!!.moreIv)
//                findNavController(More()).navigate(R.id.more)
                onItemSelected(R.id.more)
            }
            R.id.mainBtn->{
                resetBottomNav(binding!!.homeIv)
                onItemSelected(R.id.home2)
//                goToOtherActivity("DeviceToRepair")
            }

        }
    }

    companion object{
        lateinit var mContext: Context
        fun goToOtherActivity(frag:String){
            val intent = Intent(mContext, OtherActivity::class.java)
            intent.putExtra("FRAGMENT", frag)
            mContext.startActivity(intent)
        }
        fun goToOtherActivity(frag:String,title:String){
            val intent = Intent(mContext, OtherActivity::class.java)
            intent.putExtra("FRAGMENT", frag)
            intent.putExtra("arg_str", title)
            mContext.startActivity(intent)
        }
    }

}