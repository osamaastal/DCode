package com.oya.doubleq_client.ui.notifications;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import com.oya.doubleq_client.R
import com.oya.doubleq_client.classes.MyDateFormat
import com.oya.doubleq_client.classes.SharedPref
import com.oya.doubleq_client.classes.TestMsg
import com.oya.doubleq_client.classes.cons
import com.oya.doubleq_client.pojo.notifications.NotificationsItem
import java.util.*

class NotificationAdapter(private val context: Context) :
    RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {
    private var mItems: List<NotificationsItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_notifications, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.contentTv.text = mItems!![position].msg
        switchType(mItems!![position].type, holder.icon)
        /******************* TimeAgo *******************/
        val language = SharedPref.getLanguage(context)
        try {
            val LocaleBylanguageTag = Locale.forLanguageTag(language)
            val messages =
                TimeAgoMessages.Builder()
                    .withLocale(LocaleBylanguageTag).build()
            val currentTime: Long =
                MyDateFormat.formatTimeToLong(mItems!![position].date)
            val time_lib = TimeAgo.using(currentTime, messages)
            holder.timeTv.text = time_lib
        } catch (e: Exception) {
            TestMsg.show(context,""+e.message,0)
            holder.timeTv.text = mItems!![position].date.split("T")[0]
        }
    }

    private fun switchType(type: Int, icon: ImageButton) {
        when (type) {
            cons.NOTIFICATION_TYPE_ORDER -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    fun setList(mItems: List<NotificationsItem>?) {
        this.mItems = mItems
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val contentTv: TextView = itemView.findViewById(R.id.contentTv)
        val timeTv: TextView = itemView.findViewById(R.id.timeTv)
        val icon: ImageButton = itemView.findViewById(R.id.icon)
    }

}