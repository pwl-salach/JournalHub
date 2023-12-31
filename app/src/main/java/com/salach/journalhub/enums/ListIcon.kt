package com.salach.journalhub.enums

import com.salach.journalhub.R


enum class ListIcon(val id: Int) {
    HOME(R.drawable.ic_launcher_foreground),
    ALERT(R.drawable.ic_launcher_foreground),
    TIME(R.drawable.ic_launcher_foreground);

    companion object {
        fun getValues(): List<Int> {
            return values().map { it.id }
        }
    }
}