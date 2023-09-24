package com.salach.journalhub.ui.screens.pages.note

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.ui.theme.currentTypography

@Composable
fun ViewNote(note: MutableState<Note>) {
    Text(text = note.value.text, style = currentTypography().B1B)
}