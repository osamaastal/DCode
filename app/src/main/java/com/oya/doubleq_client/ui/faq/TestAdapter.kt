//#if (${PACKAGE_NAME} && ${PACKAGE_NAME}!= "")package ${PACKAGE_NAME}; #end
//
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import java.util.*
//
//class  ${NAME}(private val context: Context) : RecyclerView.Adapter< ${NAME}.MyViewHolder>() {
//    private var mItems: ArrayList< ${Model}>? = null
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): MyViewHolder {
//        return MyViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.${row}, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(
//        holder: MyViewHolder,
//        position: Int
//    ) {
//    }
//
//    override fun getItemCount(): Int {
//        return if (mItems == null) 0 else mItems!!.size
//    }
//
//    fun setList(mItems: ArrayList< ${Model}>?) {
//        this.mItems = mItems
//        notifyDataSetChanged()
//    }
//
//    inner class MyViewHolder(itemView: View) :
//        RecyclerView.ViewHolder(itemView)
//
//}