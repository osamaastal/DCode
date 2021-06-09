package com.oya.doubleq_client.data.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.oya.doubleq_client.classes.dialog.ShowMsgDialog
import com.oya.doubleq_client.pojo.general.GeneralRoot
import org.json.JSONObject
import java.lang.Exception

class HandleErrorMsg {
    companion object {
        private const val TAG = "HandleErrorMsg"
        private var showMsgDialog: ShowMsgDialog = ShowMsgDialog()

        @JvmStatic
        fun errorMsg(mContext: Context, jsonString: String) {
            try {
                val jsonObject = JSONObject(jsonString)
//                val isShow = jsonObject.getJSONObject("message_dialog").optBoolean("show")
                val msg = jsonObject.optString("message")
                Log.d(TAG, "errorMsg: $msg")
                if (true)
                    showMsgDialog.showDialog(mContext, "" + msg)
//                Toast.makeText(mContext, ""+msg, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.d(TAG, "errorMsg: ${e.message}")
            }
        }

        @JvmStatic
        fun errorMsg(mContext: Context, msg: String, show: Boolean) {
            try {
                if (show)
                    showMsgDialog.showDialog(mContext, "" + msg)
                else
                    Toast.makeText(mContext, ""+msg, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {

            }
        }

    }

}