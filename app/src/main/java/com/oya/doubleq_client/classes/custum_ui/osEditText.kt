package com.oya.doubleq_client.classes.custum_ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class osEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {
    val NOT_EMPTY = 0
    val NOT_ZERO = 1
    val START_WITH_5 = 2
    val START_WITH_05 = 3
//    val NOT_START_WITH_966 = 4
//    val NOT_START_WITH_PLUS = 5
    val LENGTH_10 = 6
//    val LENGTH_9 = 7
    val EMAIL = 8
    val PHONE_WITHOUT_INTRO = 9
    val PHONE_WITH_INTRO = 10
    var PhoneIntro = "966"

}