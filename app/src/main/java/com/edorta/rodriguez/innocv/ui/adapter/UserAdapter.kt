import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edorta.rodriguez.innocv.databinding.UserReportBinding
import com.edorta.rodriguez.innocv.model.UserModel
import com.edorta.rodriguez.innocv.ui.adapter.UserAdapterListener
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserAdapter(private val userAdapterListener: UserAdapterListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userReportList = ArrayList<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            UserReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = userReportList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userReportItem = userReportList[position]

        holder.tvName.text = userReportItem.name

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val localDate = LocalDate.parse(userReportItem.birthdate, formatter)

        holder.tvBirthday.text = localDate.toString()

        holder.deleteUserIV.setOnClickListener {
            userAdapterListener.onUserDeleteClick(userReportItem)
        }

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
        val deleteUserIV: ImageView = itemBinding.deleteUserIV
    }
}