package com.oya.doubleq_client.classes.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oya.doubleq_client.R
import com.oya.doubleq_client.pojo.sub_category.ColorsModel
import com.oya.doubleq_client.ui.product_details.ColorSelectAdapter


class BottomSheetSelectColor(private val colors:ArrayList<ColorsModel>, val listener:EventListener): BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.bs_select_model_color, container, false)
        init(v)

        return v
    }
    private fun init(v:View){
        val RVColors:RecyclerView = v.findViewById(R.id.RVColors)
        val nextBtn:Button = v.findViewById(R.id.nextBtn)
        val adapter = ColorSelectAdapter(requireContext())
        RVColors.adapter = adapter
        RVColors.layoutManager = LinearLayoutManager(requireContext())
        adapter.setList(colors)
        //actions
        nextBtn.setOnClickListener {
            for (model in adapter.getList()){
                if (model.isChecked){
                    listener.onNextColor(model.Id)
                }
            }

        }

    }

    interface EventListener{
        fun onNextColor(color_id:String)
    }
}