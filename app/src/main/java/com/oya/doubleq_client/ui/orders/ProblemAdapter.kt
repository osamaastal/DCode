package com.oya.doubleq_client.ui.orders;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import java.util.*
import kotlin.collections.ArrayList

class ProblemAdapter(private val context: Context, val mItems:ArrayList<Any>) :
    RecyclerView.Adapter<ProblemAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_text_black10, parent, false)
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleTv:TextView = itemView.findViewById(R.id.titleTv)
    }

}