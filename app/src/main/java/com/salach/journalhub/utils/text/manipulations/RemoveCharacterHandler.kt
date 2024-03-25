package com.salach.journalhub.utils.text.manipulations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue

class RemoveCharacterHandler(private val currentText: AnnotatedString, private val newText: TextFieldValue) {

    fun buildNew(): AnnotatedString {
        var shift = 0
        return buildAnnotatedString {
            for (index in currentText.indices) {
                if (newText.annotatedString.length - 1 < index - shift) {
                    continue
                }
                val newTextCounterpart = newText.annotatedString[index - shift]
                val previousChar = currentText.subSequence(index, index + 1)
                if (previousChar.text[0] == newTextCounterpart) {
                    append(previousChar)
                } else {
                    shift++
                }
            }
        }
    }
}