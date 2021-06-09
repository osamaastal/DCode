package com.oya.doubleq_client.ui.warranties;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.collections.ArrayList

class WarrantyProductAdapter(
    private val context: Context,
    val mItems: ArrayList<Any>,
    val listener: EventListener
) :
    RecyclerView.Adapter<WarrantyProductAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_warranty_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        GeneralFuns.colorImage("",holder.productColor)
        //actions
        holder.detailsBtn.setOnClickListener {
            listener.onDetails()
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.nameTv)
        val priceTv: TextView = itemView.findViewById(R.id.priceTv)
        val productColor: CircleImageView = itemView.findViewById(R.id.productColor)
        val timerImg: ImageView = itemView.findViewById(R.id.timerImg)
        val warrantyImg: ImageView = itemView.findViewById(R.id.warrantyImg)
        val productImg: ImageView = itemView.findViewById(R.id.productImg)
        val detailsBtn: ImageButton = itemView.findViewById(R.id.detailsBtn)
    }

    interface EventListener {
        fun onDetails()
    }

}