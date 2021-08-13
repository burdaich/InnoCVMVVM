package com.edorta.rodriguez.innocv.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{
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

    }
}