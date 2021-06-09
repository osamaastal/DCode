package com.oya.doubleq_client.ui.product_details;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import java.util.*

class TagAdapter(private val context: Context,val listener: EventListener) :
    RecyclerView.Adapter<TagAdapter.MyViewHolder>() {
    private var mItems: ArrayList<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_product_tag, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tagTv.text = mItems!![position]
        //actions
        holder.tagTv.setOnClickListener {
            listener.onTagCLick(mItems!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: ArrayList<String>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tagTv:TextView = itemView.findViewById(R.id.tagTv)
    }
    interface EventListener{
        fun onTagCLick(tag:String)
    }

}