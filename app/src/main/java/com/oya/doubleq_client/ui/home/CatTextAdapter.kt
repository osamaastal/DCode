package com.oya.doubleq_client.ui.home;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import com.oya.doubleq_client.pojo.category.CategoryModel
import java.util.*
import kotlin.collections.ArrayList

class CatTextAdapter(private val context: Context) :
    RecyclerView.Adapter<CatTextAdapter.MyViewHolder>() {
    private var mItems: ArrayList<CategoryModel>? = null
    private var holdersList = ArrayList<MyViewHolder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_cats_text, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holdersList.add(holder)
        holder.nameTv.text = mItems!![position].name
        //actions
        holder.nameTv.setOnClickListener {
            //reset
            for(item in holdersList){
                item.nameTv.setTextColor(context.resources.getColor(R.color.gray65))
                item.nameTv.setBackgroundResource(R.drawable.bg_trans)
            }
            //select
            holdersList[position].nameTv.setTextColor(context.resources.getColor(R.color.main_color))
            holdersList[position].nameTv.setBackgroundResource(R.drawable.bg_req_black_r12)
        }
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: ArrayList<CategoryModel>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameTv:TextView = itemView.findViewById(R.id.nameTv)
    }

}