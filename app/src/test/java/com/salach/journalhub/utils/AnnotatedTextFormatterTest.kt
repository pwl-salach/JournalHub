package com.salach.journalhub.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
    fun testHandleRemovedCharacterNotAnnotated(){
        val formatter = AnnotatedTextFormatter()

        val oldString = AnnotatedString("Hello World")
        val newText = TextFieldValue(AnnotatedString("HelloWorld"))

        assertEquals(AnnotatedString("HelloWorld"), formatter.handleRemovedCharacter(oldString, newText))
        assertEquals(AnnotatedString("HellWorld"), formatter.handleRemovedCharacter(oldString, newText.copy("HellWorld")))
        assertEquals(AnnotatedString("ellWorld"), formatter.handleRemovedCharacter(oldString, newText.copy("ellWorld")))
        assertEquals(AnnotatedString("llWorld"), formatter.handleRemovedCharacter(oldString, newText.copy("llWorld")))
        assertEquals(AnnotatedString("llWorl"), formatter.handleRemovedCharacter(oldString, newText.copy("llWorl")))
        assertEquals(AnnotatedString("llWor"), formatter.handleRemovedCharacter(oldString, newText.copy("llWor")))
    }


    // generate similar test to testHandleRemovedCharacterNotAnnotated but make sure to add various annotations like bold, italic, etc.
    // make sure to test if the annotations are removed correctly and the resulting string still contains some annotated characters

    @Test
    fun testHandleRemovedCharacterAnnotated(){
        val formatter = AnnotatedTextFormatter()


        // initialize oldString spanStyles to make it bold and italic
        val oldString = AnnotatedString("Hello World", listOf(
            AnnotatedString.Range(SpanStyle(fontWeight = FontWeight.Bold), 4, 5),
            AnnotatedString.Range(SpanStyle(fontStyle = FontStyle.Italic), 5, 6)
        ))
        // initialize newText to be the same as oldString but without the bold and italic annotations

        val newText = TextFieldValue(AnnotatedString("HelloWorld"))

        val x = formatter.handleRemovedCharacter(oldString, newText)
        assertEquals(
            AnnotatedString("HelloWorld", listOf(
                AnnotatedString.Range(SpanStyle(fontWeight = FontWeight.Bold), 4, 5)
            )),
            x
        )
    }

}
