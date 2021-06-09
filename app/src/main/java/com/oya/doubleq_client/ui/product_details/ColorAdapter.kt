package com.oya.doubleq_client.ui.product_details;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.pojo.sub_category.ColorsModel
import java.util.*

class ColorAdapter(private val context: Context) :
    RecyclerView.Adapter<ColorAdapter.MyViewHolder>() {
    private var mItems: ArrayList<ColorsModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_color, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.colorTv.text =
            if (SharedPref.getLanguage(context) == cons.AR) mItems!![position].arName
            else mItems!![position].enName
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: ArrayList<ColorsModel>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val colorTv: TextView = itemView.findViewById(R.id.colorTv)
    }

}