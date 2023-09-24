package com.salach.journalhub.ui.screens.pages.note

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.ui.components.InputLine
import com.salach.journalhub.ui.theme.currentTypography

@Composable
fun EditNote(note: MutableState<Note>) {
    val text = remember {
        mutableStateOf("")
    }
    Column() {
        InputLine(
            value = text.value,
            textStyle = currentTypography().B1B,
            modifier = Modifier,
            onValueChange = {
                text.value = it
                note.value.text = text.value
            }
        )
    }
}

//@Preview
//@Composable
//fun PreviewEditNote(){
//    EditNote()
//}