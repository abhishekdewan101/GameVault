package com.adewan.gamevault.utils

import java.time.LocalDateTime
import java.time.ZoneOffset

fun Long.getTimeAgo(): String {
  val now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
  val secondsAgo = now - this

  val minutesAgo = secondsAgo / 60
  val hoursAgo = minutesAgo / 60
  val daysAgo = hoursAgo / 24
  val weeksAgo = daysAgo / 7
  val monthsAgo = daysAgo / 30
  val yearsAgo = daysAgo / 365

  return when {
    yearsAgo > 0 -> "$yearsAgo ${if (yearsAgo == 1L) "year" else "years"} ago"
    monthsAgo > 0 -> "$monthsAgo ${if (monthsAgo == 1L) "month" else "months"} ago"
    weeksAgo > 0 -> "$weeksAgo ${if (weeksAgo == 1L) "week" else "weeks"} ago"
    daysAgo > 0 -> "$daysAgo ${if (daysAgo == 1L) "day" else "days"} ago"
    hoursAgo > 0 -> "$hoursAgo ${if (hoursAgo == 1L) "hour" else "hours"} ago"
    minutesAgo > 0 -> "$minutesAgo ${if (minutesAgo == 1L) "minute" else "minutes"} ago"
    else -> "$secondsAgo ${if (secondsAgo == 1L) "second" else "seconds"} ago"
  }
}
