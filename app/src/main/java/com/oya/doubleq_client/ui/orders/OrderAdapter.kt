package com.oya.doubleq_client.ui.orders;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.GeneralFuns
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.collections.ArrayList

class OrderAdapter(private val context: Context,val mItems: ArrayList<Any>) :
    RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_order_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        GeneralFuns.colorImage("#a24859",holder.colorImg,context)

        //RV
        //TODO get orders-list from api
        val list = arrayListOf<Any>()
        list.add(Any())
        list.add(Any())
        val problemAdapter = ProblemAdapter(context, list)
        val partAdapter = PartAdapter(context, list)
        holder.RVProblems.adapter = problemAdapter
        holder.RVProblems.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        holder.RVParts.adapter = partAdapter
        holder.RVParts.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        //actions
        holder.moreBtn.setOnClickListener {
            if (holder.moreLayout.visibility == View.GONE) {
                holder.moreLayout.visibility = View.VISIBLE
                holder.moreBtn.rotation = 180f
            }
            else{
                holder.moreLayout.visibility = View.GONE
                holder.moreBtn.rotation = 0f
            }
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val productNameTv: TextView = itemView.findViewById(R.id.productNameTv)
        val colorTv: TextView = itemView.findViewById(R.id.colorTv)
        val subTotalTv: TextView = itemView.findViewById(R.id.subTotalTv)
        val discountPercentTv: TextView = itemView.findViewById(R.id.discountPercentTv)
        val discountAmountTv: TextView = itemView.findViewById(R.id.discountAmountTv)
        val vatPercentTv: TextView = itemView.findViewById(R.id.vatPercentTv)
        val vatAmountTv: TextView = itemView.findViewById(R.id.vatAmountTv)
        val deliveryTv: TextView = itemView.findViewById(R.id.deliveryTv)
        val totalTv: TextView = itemView.findViewById(R.id.totalTv)
        val timerImg: ImageView = itemView.findViewById(R.id.timerImg)
        val warrantyImg: ImageView = itemView.findViewById(R.id.warrantyImg)
        val productImg: ImageView = itemView.findViewById(R.id.productImg)
        val colorImg: CircleImageView = itemView.findViewById(R.id.productColor)
        val moreBtn: ImageButton = itemView.findViewById(R.id.moreBtn)
        val RVProblems: RecyclerView = itemView.findViewById(R.id.RVProblems)
        val RVParts: RecyclerView = itemView.findViewById(R.id.RVParts)
        val moreLayout: LinearLayout = itemView.findViewById(R.id.moreLayout)
    }

//    private fun colorImage(colorStr:String,colorImg:CircleImageView ){
//        try {
//            val color = Color.parseColor(colorStr)
//            colorImg.circleBackgroundColor = color
//        } catch (ignore: Exception) { }
//    }

}