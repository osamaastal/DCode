package com.oya.doubleq_client.ui.cart;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.GeneralFuns
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class CartAdapter(private val context: Context, val listener: EventListener) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    private var mItems: ArrayList<Any>?=null
//    private val listHolders = ArrayList<MyViewHolder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        listHolders.add(holder)
        //TODO get color from api
        GeneralFuns.colorImage("#475960", holder.productColor,context)
        //actions
        holder.minusBtn.setOnClickListener {
            var qty = holder.qtyTv.text.toString().toInt()
            holder.qtyTv.text = (--qty).toString()
            //update total
        }
        holder.plusBtn.setOnClickListener {
            var qty = holder.qtyTv.text.toString().toInt()
            holder.qtyTv.text = (++qty).toString()
            //update total
        }
        holder.removeBtn.setOnClickListener {
            listener.onRemoveItem(position)
        }
    }

    override fun getItemCount(): Int {
        return if (mItems==null) 0 else mItems!!.size
    }

    fun setList(mItems: ArrayList<Any>?) {
        if (mItems==null)
            return
        this.mItems = mItems
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val minusBtn:ImageButton = itemView.findViewById(R.id.minusBtn)
        val plusBtn:ImageButton = itemView.findViewById(R.id.plusBtn)
        val removeBtn:ImageButton = itemView.findViewById(R.id.removeBtn)
        val qtyTv:TextView = itemView.findViewById(R.id.qtyTv)
        val priceTv:TextView = itemView.findViewById(R.id.priceTv)
        val nameTv:TextView = itemView.findViewById(R.id.nameTv)
        val img:ImageView = itemView.findViewById(R.id.img)
        val productColor:CircleImageView = itemView.findViewById(R.id.productColor)
    }

//    private fun colorImage(colorStr:String,colorImg:CircleImageView ){
//        try {
//            val color = Color.parseColor(colorStr)
//            colorImg.circleBackgroundColor = color
//        } catch (ignore: Exception) {
//            TestMsg.show(context ,"${ignore.message}", 0)
//        }
//    }
    fun removeItem(position:Int){
        mItems!!.remove(position)
        notifyDataSetChanged()
    }

    interface EventListener{
        fun onRemoveItem(position:Int)
    }

}