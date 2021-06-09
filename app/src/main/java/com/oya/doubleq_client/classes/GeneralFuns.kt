package com.oya.doubleq_client.classes

import android.content.Context
import android.graphics.Color
import androidx.cardview.widget.CardView
import de.hdodenhof.circleimageview.CircleImageView
import java.io.Serializable

class GeneralFuns : Serializable {
    companion object {
        fun colorImage(colorStr: String, colorImg: CircleImageView, context: Context) {
            try {
                val color = Color.parseColor(colorStr)
                colorImg.circleBackgroundColor = color
            } catch (ignore: Exception) {
                TestMsg.show(context, "${ignore.message}", 0)
            }
        }

        fun colorImage(colorStr: String, colorImg: CardView, context: Context) {
            try {
                val color = Color.parseColor(colorStr)
                colorImg.setCardBackgroundColor(color)
            } catch (ignore: Exception) {
                TestMsg.show(context, "${ignore.message}", 0)
            }
        }
    }

}