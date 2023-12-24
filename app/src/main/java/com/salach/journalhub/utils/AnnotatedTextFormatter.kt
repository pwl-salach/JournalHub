package com.salach.journalhub.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle


class AnnotatedTextFormatter {
    private val regularTextOptions = TextFormatterOptions()
    private val selectionOptions = TextFormatterOptions()
//    private var isSelectionEnabled = false

    fun annotateString(previousText: AnnotatedString, newText: TextFieldValue): AnnotatedString {
//        isSelectionEnabled = newText.selection != TextRange.Zero
//        if (!isSelectionEnabled && getActiveOptions() == selectionOptions){
//            selectionOptions.resetAll()
//        }
        if (previousText.length < newText.annotatedString.length){
            return handleNewCharacter(previousText, newText)
        } else if (previousText.length > newText.annotatedString.length) {
            return handleRemovedCharacter(previousText, newText)
        } else {
            return previousText
        }
    }

    fun handleNewCharacter(previousText: AnnotatedString, newText: TextFieldValue): AnnotatedString{
        val newPart = newText.annotatedString.subSequence(previousText.length, newText.annotatedString.length)
        val builder = AnnotatedString.Builder()
        builder.append(previousText)
        val decoration = getTextAnnotationDetails()
        if(regularTextOptions.isAnyStyleEnabled()){
            builder.withStyle(
                style = SpanStyle(
                    fontWeight = decoration.fontWeight,
                    fontStyle = decoration.fontStyle,
                    textDecoration = decoration.decoration
                ),
            ) {
                append(newPart)
            }
        } else {
            builder.append(newPart)
        }
        return builder.toAnnotatedString()
    }

    fun handleRemovedCharacter(previousText: AnnotatedString, newText: TextFieldValue): AnnotatedString{
        var shift = 0
        return buildAnnotatedString {
            for(index in previousText.indices) {
                if (newText.annotatedString.length - 1 < index - shift) {
                    continue
                }
                val newTextCounterpart = newText.annotatedString[index - shift]
                val previousChar = previousText.subSequence(index, index + 1)
                if (previousChar.text[0] == newTextCounterpart) {
                    append(previousChar)
                } else {
                    shift++
                }
            }
        }
    }

    fun handleAnnotateSelection(previousText: AnnotatedString, newText: TextFieldValue): AnnotatedString {
        val builder = AnnotatedString.Builder()
        builder.append(previousText.subSequence(0, newText.selection.start))
        val decoration = getTextAnnotationDetails()
        val newPart = previousText.subSequence(newText.selection.start, newText.selection.end)
        if(regularTextOptions.isAnyStyleEnabled()) {
            builder.withStyle(
                style = SpanStyle(
                    fontWeight = decoration.fontWeight,
                    fontStyle = decoration.fontStyle,
                    textDecoration = decoration.decoration
                ),
            ) {
                append(newPart)
            }
        } else {
            builder.append(newPart)
        }
        builder.append(previousText.subSequence(newText.selection.end, previousText.length))
        return builder.toAnnotatedString()
    }

    fun getTextAnnotationDetails(): AnnotationDetails{
        val details = AnnotationDetails()
        if (getActiveOptions().boldEnabled) details.fontWeight = FontWeight.Bold
        if (getActiveOptions().italicEnabled) details.fontStyle = FontStyle.Italic
        if (getActiveOptions().underlineEnabled){
            details.decoration += TextDecoration.Underline
        }
        if (getActiveOptions().strikethroughEnabled){
            details.decoration += TextDecoration.LineThrough
        }
        return details
    }

    private fun getActiveOptions(isSelectionEnabled: Boolean = false): TextFormatterOptions{
        return if (isSelectionEnabled) selectionOptions else regularTextOptions
    }

    fun switchBold(){
        getActiveOptions().boldEnabled = !getActiveOptions().boldEnabled
    }
    fun switchItalic(){
        getActiveOptions().italicEnabled = !getActiveOptions().italicEnabled
    }
    fun switchUnderline(){
        getActiveOptions().underlineEnabled = !getActiveOptions().underlineEnabled
    }
    fun switchStrikethrough(){
        getActiveOptions().strikethroughEnabled = !getActiveOptions().strikethroughEnabled
    }
}