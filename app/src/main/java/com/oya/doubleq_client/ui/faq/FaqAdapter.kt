package com.oya.doubleq_client.ui.faq;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import com.oya.doubleq_client.pojo.faq.ConstantItem

class FaqAdapter(private val context: Context) : RecyclerView.Adapter<FaqAdapter.MyViewHolder>() {
    private var mItems: List<ConstantItem>? = null
    private var holderList = arrayListOf<MyViewHolder>()
    private val MAX_L = 50
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_faq, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holderList.add(holder)
        holder.titleTv.text = mItems!![position].name
        if (mItems!![position].details.length > MAX_L)
            holder.detailsTv.text = mItems!![position].details.substring(0, MAX_L-3) + "..."
        else
            holder.detailsTv.text = mItems!![position].details
        //action
        holder.moreBtn.setOnClickListener {
            if (holderList[position].detailsTv.text.length > MAX_L)
                holderList[position].detailsTv.text = mItems!![position].details.substring(0, MAX_L-3)+ "..."
            else
                holderList[position].detailsTv.text = mItems!![position].details
        }
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: List<ConstantItem>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var titleTv: TextView = itemView.findViewById(R.id.titleTv)
        var detailsTv: TextView = itemView.findViewById(R.id.detailsTv)
        var moreBtn: ImageButton = itemView.findViewById(R.id.moreBtn)
    }

}