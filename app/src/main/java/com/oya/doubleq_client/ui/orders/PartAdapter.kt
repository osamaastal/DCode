package com.oya.doubleq_client.ui.orders;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import java.util.*
import kotlin.collections.ArrayList

class PartAdapter(private val context: Context, val mItems:ArrayList<Any>) : RecyclerView.Adapter<PartAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_order_details_parts, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return  mItems.size
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)
        val contentTv: TextView = itemView.findViewById(R.id.contentTv)
        val percentTv: TextView = itemView.findViewById(R.id.percentTv)
        val warrantyTv: TextView = itemView.findViewById(R.id.warrantyTv)
        val fixTimeTv: TextView = itemView.findViewById(R.id.fixTimeTv)
        val warrantyLayout: LinearLayout = itemView.findViewById(R.id.warrantyLayout)
        val fixTimeLayout: LinearLayout = itemView.findViewById(R.id.fixTimeLayout)
    }

}