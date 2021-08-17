import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.security.interfaces.RSAMultiPrimePrivateCrtKey
import java.util.*

class DatePickerFragment(private val year: Int, private val month: Int, private val day: Int) :
    DialogFragment() {

    private var listener: DatePickerDialog.OnDateSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()

        return DatePickerDialog(requireActivity(), listener, year, month, day)
    }

    companion object {
        fun newInstance(
            year: Int,
            month: Int,
            day: Int,
            listener: DatePickerDialog.OnDateSetListener,
        ): DatePickerFragment {
            val fragment = DatePickerFragment(
                year,
                month-1,
                day
            )
            fragment.listener = listener
            return fragment
        }
    }


}