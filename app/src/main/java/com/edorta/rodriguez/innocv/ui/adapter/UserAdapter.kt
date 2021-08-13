import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edorta.rodriguez.innocv.databinding.UserReportBinding
import com.edorta.rodriguez.innocv.ui.adapter.UserAdapterListener
import com.edorta.rodriguez.innocv.utils.Utils.Companion.formattedDate

import com.edorta.rodriguez.innocv.model.UserModel

class UserAdapter(private val userAdapterListener: UserAdapterListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userReportList = ArrayList<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            UserReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }
//        LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
//    )

    override fun getItemCount() = userReportList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userReportItem = userReportList[position]

        holder.tvName.text = userReportItem.name
        holder.tvBirthday.text = userReportItem.birthdate

        /*userReportItem.birthdate?.let {
            val formattedDate =
                formattedDate(it)
            holder.tvBirthday.text = formattedDate
        }*/


        holder.itemView.setOnClickListener {
            userAdapterListener.onUserAdapterClick(userReportItem)
        }
    }

    fun updateData(data: List<UserModel>?) {
        data?.let {
            userReportList.clear()
            userReportList.addAll(data)
            notifyDataSetChanged()
        }

    }


    class ViewHolder(itemBinding: UserReportBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val tvName: TextView = itemBinding.tvName
        val tvBirthday: TextView = itemBinding.tvBirthday
    }
}