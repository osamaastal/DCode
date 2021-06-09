package com.oya.doubleq_client.ui.orders;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import java.util.*
import kotlin.collections.ArrayList

class OrderDateAdapter(private val context: Context) :
    RecyclerView.Adapter<OrderDateAdapter.MyViewHolder>() {
    private var mItems: ArrayList<Any>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_order_date, parent, false)
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
        val adapter = OrderAdapter(context, list)
        holder.RVOrders.adapter = adapter
        holder.RVOrders.layoutManager = object : LinearLayoutManager(context) {
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
        val dateTv: TextView = itemView.findViewById(R.id.dateTv)
        val numOrdersTv: TextView = itemView.findViewById(R.id.numOrdersTv)
        val RVOrders: RecyclerView = itemView.findViewById(R.id.RVOrders)
    }

}