package com.salach.journalhub.utils

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.salach.journalhub.db.models.Note


class AnnotatedTextTransformation(
    private val annotator: MutableState<AnnotatedTextFormatter>,
    private val previousText: MutableState<AnnotatedString>,
    private val note: MutableState<Note>,
    private val currentText: MutableState<TextFieldValue>
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val annotatedText = annotator.value.annotateString(
            previousText.value, currentText.value
        )
        note.value.text = annotatedText
        previousText.value = annotatedText
        return TransformedText(
            text = annotatedText,
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = offset
                override fun transformedToOriginal(offset: Int): Int = offset
            }
        )
    }
}