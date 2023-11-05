package com.salach.journalhub.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle


class AnnotatedTextFormatter {
    private var boldEnabled: Boolean = false
    private var italicEnabled: Boolean = false
    private var underlineEnabled: Boolean = false
    private var strikethroughEnabled: Boolean = false

    fun annotateString(previousText: AnnotatedString, newText: TextFieldValue): AnnotatedString {
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
        if(boldEnabled || italicEnabled || underlineEnabled || strikethroughEnabled){
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
            for(index in previousText.indices){
                if (newText.annotatedString.length - 1< index - shift){
                    continue
                }
                val newTextCounterpart = newText.annotatedString[index - shift]
                val previousChar = previousText.subSequence(index, index + 1)
                if (previousChar.text[0] == newTextCounterpart) {
                    if(previousChar.spanStyles.isEmpty()){
                        append(previousChar)
                    } else {
                        withStyle(style = previousText.spanStyles[0].item) {
                            append(previousChar)
                        }
                    }
                } else {
                    shift++
                }
            }
        }
    }

    fun handleAnnotateSelection(){

    }

    fun getTextAnnotationDetails(): AnnotationDetails{
        val details = AnnotationDetails()
        if (boldEnabled) details.fontWeight = FontWeight.Bold
        if (italicEnabled) details.fontStyle = FontStyle.Italic
        if (underlineEnabled){
            details.decoration += TextDecoration.Underline
        }
        if (strikethroughEnabled){
            details.decoration += TextDecoration.LineThrough
        }
        return details
    }


    fun switchBold(){ boldEnabled = !boldEnabled }
    fun switchItalic(){ italicEnabled = !italicEnabled }
    fun switchUnderline(){ underlineEnabled = !underlineEnabled }
    fun switchStrikethrough(){ strikethroughEnabled = !strikethroughEnabled }
}