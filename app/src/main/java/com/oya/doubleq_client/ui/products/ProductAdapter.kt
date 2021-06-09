package com.oya.doubleq_client.ui.products;

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.pojo.product.ProductModel
import com.oya.doubleq_client.ui.a_main.MainActivity
import com.oya.doubleq_client.ui.a_other.OtherActivity
import com.oya.doubleq_client.ui.home.HomeProducts
import com.oya.doubleq_client.ui.product_details.ProductDetails
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class ProductAdapter(val context: Context, val listener: EventListener, private val itemViewType: Int) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private var mItems: ArrayList<ProductModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return if (itemViewType == cons.VIEW_TYPE_HORIZONTAL)
            MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_favorites2, parent, false)
            )
        else MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_favorites, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.productNameTv.text = mItems!![position].name
        val price = "${String.format(Locale.US,"%.2f", mItems!![position].price)} ${context.getString(R.string.sar)}"
        holder.priceTv.text = price
        Glide.with(context)
            .load(mItems!![position].image)
            .error(R.drawable.defualt)
            .into(holder.img)
        if(mItems!![position].isLike){
            holder.likeBtn.setImageResource(R.drawable.ic_like)
        }else{
            holder.likeBtn.setImageResource(R.drawable.ic_dislike)
        }
        //actions
        holder.likeBtn.setOnClickListener {
            //if isLike > set isLike false (else set isLike true)
//            mItems!![position].isLike = !mItems!![position].isLike
            listener.onLike(mItems!![position].Id, itemViewType)
        }
        holder.itemView.setOnClickListener {
            listener.onProductDetails(mItems!![position].Id)
        }
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: ArrayList<ProductModel>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }
    fun toggleIcon(product_id: String){
        for (model in mItems!!){
            if (model.Id == product_id){
                model.isLike = !model.isLike
                notifyDataSetChanged()
            }
        }
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val likeBtn: ImageButton = itemView.findViewById(R.id.likeBtn)
        val productNameTv: TextView = itemView.findViewById(R.id.productNameTv)
        val priceTv: TextView = itemView.findViewById(R.id.priceTv)
        val img: ImageView = itemView.findViewById(R.id.img)

    }

    interface EventListener {
        fun onLike(product_id:String, itemViewType:Int)
        fun onProductDetails(product_id: String)
    }

}