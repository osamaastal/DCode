package com.oya.doubleq_client.ui.home;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.GeneralFuns
import com.oya.doubleq_client.pojo.types.TypeModel
import com.oya.doubleq_client.ui.a_main.MainActivity
import java.util.*

class TypeAdapter(private val context: Context) :
    RecyclerView.Adapter<TypeAdapter.MyViewHolder>() {
    private var mItems: ArrayList<TypeModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_home_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTv.text = mItems!![position].name
        holder.detailsTv.text = mItems!![position].details
        GeneralFuns.colorImage(mItems!![position].color, holder.card, context)
        Glide.with(context)
            .load(mItems!![position].image)
            .into(holder.img)
        //actions
        holder.itemView.setOnClickListener {
            MainActivity.goToOtherActivity("DeviceToRepair")
        }
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: ArrayList<TypeModel>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleTv:TextView = itemView.findViewById(R.id.titleTv)
        val detailsTv:TextView = itemView.findViewById(R.id.detailsTv)
        val img:ImageView = itemView.findViewById(R.id.img)
        val card:CardView = itemView.findViewById(R.id.card)
    }

}