package com.example.jansenapp.base.ext.component

import android.content.Context
import android.text.format.DateUtils
import com.example.jansenapp.domain.enums.DateTimeFormat
import java.text.SimpleDateFormat
import java.util.*

fun Date.toRelativeDateTime(context: Context): String? = DateUtils
    .getRelativeDateTimeString(
        context, this.time,
        1000,
        DateUtils.HOUR_IN_MILLIS,
        DateUtils.FORMAT_SHOW_WEEKDAY or DateUtils.FORMAT_SHOW_TIME
    )
    .toString()


fun Date.toReadableString(dateTimeFormat: DateTimeFormat): String? {
    val simpleDateFormat = SimpleDateFormat(dateTimeFormat.value, Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun String.toDate(dateTimeFormat: DateTimeFormat): Date? = try {
    val simpleDateFormat = SimpleDateFormat(dateTimeFormat.value, Locale.getDefault())
    val date = this.replace("Z", "+00:00")
    simpleDateFormat.parse(date)
} catch (e: Throwable) {
    e.printStackTrace()
    null
}