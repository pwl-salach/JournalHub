package com.salach.journalhub.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle


class AnnotatedTextFormatter {
    private var boldEnabled: Boolean = false
    private var italicEnabled: Boolean = false
    private var underlineEnabled: Boolean = false
    private var strikethroughEnabled: Boolean = false

    fun annotateString(previousText: AnnotatedString, newText: AnnotatedString, selection: TextRange): AnnotatedString {
        val builder = AnnotatedString.Builder()
        val fontWeight = if (boldEnabled) FontWeight.Bold else FontWeight.Normal
        val fontStyle = if (italicEnabled) FontStyle.Italic else FontStyle.Normal
        var textDecoration = TextDecoration.None
        if (underlineEnabled){
            textDecoration += TextDecoration.Underline
        }
        if (strikethroughEnabled){
            textDecoration += TextDecoration.LineThrough
        }

        val newPart = newText.subSequence(previousText.length, newText.length)
        builder.append(previousText)
        if(boldEnabled || italicEnabled || underlineEnabled || strikethroughEnabled){
            builder.withStyle(
                style = SpanStyle(
                    fontWeight = fontWeight,
                    fontStyle = fontStyle,
                    textDecoration = textDecoration
                ),
            ) {
                append(newPart)
            }
        } else {
            builder.append(newPart)
        }

        return builder.toAnnotatedString()
    }

    fun switchBold(){ boldEnabled = !boldEnabled }
    fun switchItalic(){ italicEnabled = !italicEnabled }
    fun switchUnderline(){ underlineEnabled = !underlineEnabled }
    fun switchStrikethrough(){ strikethroughEnabled = !strikethroughEnabled }
}