package com.example.myapplication.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class DateUtils {

    companion object {
        fun formatDate(date: LocalDate): String {
            return date.format(DateTimeFormatter.ofPattern(getDateFormatString(), Locale.getDefault()))
        }

        fun formatDate(year: Int, month: Int, day: Int): String {
            return formatDate(LocalDate.of(year, month, day))
        }

        fun getDateFromString(dateString: String): LocalDate{
            return LocalDate.parse(dateString, getDateFormat())
        }

        fun getDateFormatString(): String{
            val locale = Locale.getDefault()
            return when (locale.country){
                "US" -> "MM/dd/yyyy"
                else -> "dd/MM/yyyy"
            }
        }

        fun getDateFormat(): DateTimeFormatter {
            return DateTimeFormatter.ofPattern(getDateFormatString())
        }
    }
}