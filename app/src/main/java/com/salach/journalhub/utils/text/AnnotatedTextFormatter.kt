package com.salach.journalhub.utils.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.salach.journalhub.utils.text.manipulations.NewCharacterHandler
import com.salach.journalhub.utils.text.manipulations.RemoveCharacterHandler


class AnnotatedTextFormatter(initialText: AnnotatedString) {
    private val regularTextOptions = TextFormatterOptions()
    private val selectionOptions = TextFormatterOptions()
    private var currentText: AnnotatedString = initialText
    private var selection: TextRange = TextRange.Zero

    fun annotateString(changedTextField: TextFieldValue): AnnotatedString {
        selection = changedTextField.selection
//        if (!isSelectionEnabled && getActiveOptions() == selectionOptions){
//            selectionOptions.resetAll()
//        }
        var newVal = currentText
        if (currentText.length < changedTextField.annotatedString.length){
            newVal = handleNewCharacter(changedTextField)
        } else if (currentText.length > changedTextField.annotatedString.length) {
            newVal = handleRemovedCharacter(changedTextField)
        }
        currentText = newVal
        return currentText
    }

    private fun handleNewCharacter(newText: TextFieldValue): AnnotatedString{
        return NewCharacterHandler(
            currentText = currentText,
            newText = newText,
            annotationDetails = getTextAnnotationDetails()
        ).buildNew()
    }

    fun handleRemovedCharacter(newText: TextFieldValue): AnnotatedString{
        return RemoveCharacterHandler(
            currentText = currentText,
            newText = newText
        ).buildNew()
    }

    private fun handleAnnotateSelection() {
        if (selection == TextRange.Zero){ return }
        val builder = AnnotatedString.Builder()
        builder.append(currentText.subSequence(0, selection.start))
        val decoration = getTextAnnotationDetails()
        val newPart = currentText.subSequence(selection.start, selection.end)
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
        builder.append(currentText.subSequence(selection.end, currentText.length))
        currentText = builder.toAnnotatedString()
    }

    private fun getTextAnnotationDetails(): AnnotationDetails {
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

    private fun getActiveOptions(isSelectionEnabled: Boolean = false): TextFormatterOptions {
        return if (isSelectionEnabled) selectionOptions else regularTextOptions
    }

    fun switchBold(){
        getActiveOptions().boldEnabled = !getActiveOptions().boldEnabled
        handleAnnotateSelection()
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