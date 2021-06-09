package com.oya.doubleq_client.ui.a_other

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.oya.doubleq_client.R
import com.oya.doubleq_client.databinding.ActivityOtherBinding
import com.oya.doubleq_client.ui.contact_us.ContactUs
import com.oya.doubleq_client.ui.define_problems.DefineProblems
import com.oya.doubleq_client.ui.device_to_repair.DeviceToRepair
import com.oya.doubleq_client.ui.faq.FAQ
import com.oya.doubleq_client.ui.favorites.Favorites
import com.oya.doubleq_client.ui.need_fix.NeedFix
import com.oya.doubleq_client.ui.notifications.Notifications
import com.oya.doubleq_client.ui.order_details.OrderDetailsProduct
import com.oya.doubleq_client.ui.order_details.OrderDetailsWarranty_Product
import com.oya.doubleq_client.ui.order_details.OrderDetailsWarranty_Repair
import com.oya.doubleq_client.ui.order_review.OrderReview
import com.oya.doubleq_client.ui.policy.Policy
import com.oya.doubleq_client.ui.product_details.ProductDetails
import com.oya.doubleq_client.ui.products.Products
import com.oya.doubleq_client.ui.profile.Profile
import com.oya.doubleq_client.ui.proposal_details.ProposalDetails
import com.oya.doubleq_client.ui.search.Search
import com.oya.doubleq_client.ui.ver_code.VerCode
import com.oya.doubleq_client.ui.warranties.Warranties

class OtherActivity : AppCompatActivity() {
    private lateinit var binding:ActivityOtherBinding
    private var fragmentManager: FragmentManager? = null
    private var fragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_other)
        fragmentManager = supportFragmentManager
        if (intent != null) {
            if (intent.getStringExtra("FRAGMENT") != null) {
                when {
                    intent.getStringExtra("FRAGMENT") == "Policy" -> {
                        val type = intent.getStringExtra("arg_str").toString()
                        fragment = Policy.newInstance(type)
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "ContactUs" -> {
                        fragment = ContactUs()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "DefineProblems" -> {
                        fragment = DefineProblems()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "DeviceToRepair" -> {
                        fragment = DeviceToRepair()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "FAQ" -> {
                        fragment = FAQ()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "Favorites" -> {
                        fragment = Favorites.newInstance(intent.getStringExtra("arg_str").toString())
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "NeedFix" -> {
                        fragment = NeedFix()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "Notifications" -> {
                        fragment = Notifications()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "OrderDetailsProduct" -> {
                        fragment = OrderDetailsProduct()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "OrderDetailsWarranty_Product" -> {
                        fragment = OrderDetailsWarranty_Product()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "OrderDetailsWarranty_Repair" -> {
                        fragment = OrderDetailsWarranty_Repair()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "OrderReview" -> {
                        fragment = OrderReview()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "ProductDetails" -> {
                        val product_id=  intent.getStringExtra("arg_str")
                        fragment = ProductDetails.newInstance(product_id!!)
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "Products" -> {
                        fragment =  Products()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "Profile" -> {
                        fragment = Profile()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "ProposalDetails" -> {
                        fragment = ProposalDetails()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "Search" -> {
                        fragment = Search()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "VerCode" -> {
                        fragment = VerCode()
                        switch_fragment(fragment)
                    }
                    intent.getStringExtra("FRAGMENT") == "Warranties" -> {
                        fragment = Warranties()
                        switch_fragment(fragment)
                    }
                }
            }
        }
    }
    private fun switch_fragment(fragment: Fragment?) {
        val transaction: FragmentTransaction =
            fragmentManager!!.beginTransaction()
        transaction.replace(R.id.container, fragment!!).commit()
//        transaction.addToBackStack(null);
    }
}