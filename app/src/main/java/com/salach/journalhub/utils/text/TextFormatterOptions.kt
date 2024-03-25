package com.salach.journalhub.utils.text

class TextFormatterOptions {
    var boldEnabled: Boolean = false
    var italicEnabled: Boolean = false
    var underlineEnabled: Boolean = false
    var strikethroughEnabled: Boolean = false

    fun isAnyStyleEnabled(): Boolean {
        return boldEnabled || italicEnabled || underlineEnabled || strikethroughEnabled
    }

    fun resetAll() {
        boldEnabled = false
        italicEnabled = false
        underlineEnabled = false
        strikethroughEnabled = false
    }
}