package com.example.jansenapp.base.ext.component

import android.content.Context
import android.text.format.DateUtils
import java.util.Date

fun Date.toRelativeDateTime(context: Context): String? = DateUtils
    .getRelativeDateTimeString(
        context, this.time,
        1000,
        DateUtils.HOUR_IN_MILLIS,
        DateUtils.FORMAT_SHOW_WEEKDAY or DateUtils.FORMAT_SHOW_TIME
    )
    .toString()