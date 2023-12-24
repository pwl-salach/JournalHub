package com.salach.journalhub.utils

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.salach.journalhub.db.models.Note


class AnnotatedTextTransformation(
    private val annotator: AnnotatedTextFormatter,
    private val note: Note,
    private val currentText: TextFieldValue
) : VisualTransformation {
    private var previousText: AnnotatedString = note.text

    override fun filter(text: AnnotatedString): TransformedText {
        val annotatedText = annotator.annotateString(
            previousText, currentText
        )
        note.text = annotatedText
        previousText = annotatedText
        return TransformedText(
            text = annotatedText,
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = offset
                override fun transformedToOriginal(offset: Int): Int = offset
            }
        )
    }
}