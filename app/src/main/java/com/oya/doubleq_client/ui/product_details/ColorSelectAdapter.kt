package com.oya.doubleq_client.ui.product_details;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.pojo.sub_category.ColorsModel

class ColorSelectAdapter(private val context: Context) :
    RecyclerView.Adapter<ColorSelectAdapter.MyViewHolder>() {
    private var mItems: ArrayList<ColorsModel>? = null
    private  var listHolders= ArrayList<MyViewHolder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_color_select, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        GeneralFuns.colorImage("#A32222" ,holder.colorImg, context)
        listHolders.add(holder)
        //action
        listHolders[position].rb.text = if (SharedPref.getLanguage(context) == cons.AR) mItems!![position].arName
        else mItems!![position].enName

        listHolders[position].rb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                TestMsg.show(context, "checked", 0)
                for (i in 0 until mItems!!.size) {
                    mItems!![i].isChecked = false
                }
                for (i in 0 until listHolders.size){
                    listHolders[i].rb.isChecked = false
                }
                listHolders[position].rb.isChecked = true
                mItems!![position].isChecked = isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: ArrayList<ColorsModel>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<ColorsModel> {
        return if (mItems != null) mItems!!
        else ArrayList()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        //        val colorImg: CircleImageView = itemView.findViewById(R.id.colorImg)
        val rb: RadioButton = itemView.findViewById(R.id.rb)
    }

}