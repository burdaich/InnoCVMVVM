package com.edorta.rodriguez.innocv.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import com.edorta.rodriguez.innocv.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun formattedDate(date: String): String {
            val date = entryFormatDate(date)

            val simpleDateFormat2 =
                SimpleDateFormat("dd MMM yyyy")
            simpleDateFormat2.timeZone = TimeZone.getDefault()
            return simpleDateFormat2.format(date)
        }

        @SuppressLint("SimpleDateFormat")
        fun entryFormatDate(date: String): Date? {
            val simpleDateFormat =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

            return simpleDateFormat.parse(date)
        }


        fun Fragment.dialogYesNo(
            title: String,
            message: String,
            yesFuncParameter: Any,
            yesFunc: (param: Any) -> Unit,
            noFunc: (() -> Unit)? = null,
            yesMessage: String? = resources.getString(R.string.yes),
            noMessage: String? = resources.getString(R.string.no)
        ) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(yesMessage) { dialog, _ ->
                    yesFunc(yesFuncParameter)
                    dialog.dismiss()
                }
                .setNegativeButton(noMessage) { dialog, _ ->
                    noFunc?.let { noFunc() }
                    dialog.dismiss()
                }
                .show()
        }



        fun foo(msg: String, bar: (input: Any) -> Unit) {
            bar(msg)
        }

    }




}