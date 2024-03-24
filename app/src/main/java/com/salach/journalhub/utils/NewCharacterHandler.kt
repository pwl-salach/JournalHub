package com.salach.journalhub.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle

class NewCharacterHandler(
    private val currentText: AnnotatedString,
    private val newText: TextFieldValue,
    private val annotationDetails: AnnotationDetails,
){
    private val builder = AnnotatedString.Builder()

    fun build(): AnnotatedString {
        val newPartRange = getNewPartRange()
        appendBeginningOfOriginalText(newPartRange)
        appendNewPart(newPartRange)
        appendEndingOfOriginalText(newPartRange)
        return builder.toAnnotatedString()
    }

    private fun getNewPartRange(): IntRange {
        // selection always points to the end of the new character
        return IntRange(newText.selection.start - 1, newText.selection.start)
    }

    private fun appendBeginningOfOriginalText(newPartRange: IntRange){
        builder.append(
            currentText.subSequence(0, newPartRange.first)
        )
    }

    private fun appendNewPart(newPartRange: IntRange){
        val newPart = newText.annotatedString.subSequence(newPartRange.first, newPartRange.last)
        builder.withStyle(
            style = SpanStyle(
                fontWeight = annotationDetails.fontWeight,
                fontStyle = annotationDetails.fontStyle,
                textDecoration = annotationDetails.decoration
            ),
        ) {
            append(newPart)
        }
    }

    private fun appendEndingOfOriginalText(newPartRange: IntRange){
        builder.append(currentText.subSequence(newPartRange.first, currentText.length))
    }
}