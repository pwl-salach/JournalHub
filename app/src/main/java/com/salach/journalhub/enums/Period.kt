package com.salach.journalhub.enums

enum class Period {
    NONE,
    DAILY,
    WEEKLY,
    MONTHLY,
    ANNUALLY;

    companion object {
        fun getDisplayable(): List<Period>{
            return values().filterNot { it == NONE }
        }
    }
}