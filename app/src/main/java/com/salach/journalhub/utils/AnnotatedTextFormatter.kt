package com.salach.journalhub.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle


class AnnotatedTextFormatter {
    var boldEnabled: Boolean = false
    var italicEnabled: Boolean = false
    var underlineEnabled: Boolean = false
    var strikethroughEnabled: Boolean = false

    fun annotateString(previousText: AnnotatedString, newText: AnnotatedString, selection: TextRange): AnnotatedString {
        val builder = AnnotatedString.Builder()

        val newPart = newText.subSequence(previousText.length, newText.length)
        builder.append(previousText)
        if(boldEnabled){
            builder.withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
            ) {
                append(newPart)
            }
        } else {
            builder.append(newPart)
        }

        return builder.toAnnotatedString()
    }
}