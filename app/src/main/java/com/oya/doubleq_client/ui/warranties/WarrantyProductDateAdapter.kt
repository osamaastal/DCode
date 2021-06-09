package com.oya.doubleq_client.ui.warranties;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import java.util.*

class WarrantyProductDateAdapter(
    private val context: Context,
    val listener: WarrantyProductAdapter.EventListener
) :
    RecyclerView.Adapter<WarrantyProductDateAdapter.MyViewHolder>() {
    private var mItems: ArrayList<Any>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_warranty_product_date, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //RV
        //TODO get orders-list from api
        val list = arrayListOf<Any>()
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        val adapter = WarrantyProductAdapter(context, list, listener)
        holder.RVProducts.adapter = adapter
        holder.RVProducts.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        //actions
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: ArrayList<Any>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var dateTv: TextView = itemView.findViewById(R.id.dateTv)
        var RVProducts: RecyclerView = itemView.findViewById(R.id.RVProducts)
    }

}