package com.oya.doubleq_client.classes.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.oya.doubleq_client.R

class ShowMsgDialog {

    private var popup: Dialog? = null

    fun showDialog(context: Context?, msg: String?) {
        if (popup == null) {
            popup = Dialog(context!!)
            popup!!.setContentView(R.layout.popup_msg)
            popup!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            popup!!.setCancelable(false)
        }
        popup!!.show()
        val msgTv = popup!!.findViewById<TextView>(R.id.msgTv)
        val okBtn = popup!!.findViewById<Button>(R.id.okBtn)
        msgTv.text = msg
        okBtn.setText(R.string.ok)
        okBtn.setOnClickListener { dismiss() }
    }

    private fun dismiss() {
        popup!!.dismiss()
    }
}