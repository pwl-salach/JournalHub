package com.salach.journalhub.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import org.junit.Assert.assertEquals
import org.junit.Test

class AnnotatedTextFormatterTest {

    @Test
    fun testHandleRemovedCharacterNotAnnotatedStart(){
        val formatter = AnnotatedTextFormatter()

        val oldString = AnnotatedString("Hello")
        val newText = TextFieldValue(AnnotatedString("ello"))

        assertEquals(AnnotatedString("ello"), formatter.handleRemovedCharacter(oldString, newText))
        assertEquals(AnnotatedString("llo"), formatter.handleRemovedCharacter(oldString, newText.copy("llo")))
    }

    @Test
    fun testHandleRemovedCharacterNotAnnotatedEnd(){
        val formatter = AnnotatedTextFormatter()

        val oldString = AnnotatedString("Hello")
        val newText = TextFieldValue(AnnotatedString("Hell"))

        assertEquals(formatter.handleRemovedCharacter(oldString, newText), AnnotatedString("Hell"))
        assertEquals(formatter.handleRemovedCharacter(oldString, newText.copy("Hel")), AnnotatedString("Hel"))
    }

    @Test
    fun testHandleRemovedCharacterNotAnnotatedMiddle(){
        val formatter = AnnotatedTextFormatter()

        val oldString = AnnotatedString("Hello")
        val newText = TextFieldValue(AnnotatedString("Helo"))

        assertEquals(AnnotatedString("Helo"), formatter.handleRemovedCharacter(oldString, newText))
        assertEquals(AnnotatedString("Hlo"), formatter.handleRemovedCharacter(oldString, newText.copy("Hlo")))
    }

    @Test
    fun testHandleRemovedCharacterNotAnnotatedMixed(){
        val formatter = AnnotatedTextFormatter()

        val oldString = AnnotatedString("Hello")
        val newText = TextFieldValue(AnnotatedString("Helo"))

        assertEquals(AnnotatedString("Helo"), formatter.handleRemovedCharacter(oldString, newText))
        assertEquals(AnnotatedString("Hel"), formatter.handleRemovedCharacter(oldString, newText.copy("Hel")))
        assertEquals(AnnotatedString("el"), formatter.handleRemovedCharacter(oldString, newText.copy("el")))
    }
}
