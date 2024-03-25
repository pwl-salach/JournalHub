package com.salach.journalhub.ui.screens.pages.note

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.salach.journalhub.db.models.Note

@Composable
fun ViewNote(note: Note) {
    Text(
        text = note.text,
//        visualTransformation = AnnotatedTextTransformation()
    )
}