package com.salach.journalhub.ui.screens.pages.note

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.ui.components.KeyboardToolbar
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.utils.AnnotatedTextFormatter
import com.salach.journalhub.utils.AnnotatedTextTransformation


@Composable
fun EditNote(note: Note) {
    var currentTextField by remember { mutableStateOf(TextFieldValue(note.text.text)) }
    val annotator by remember {
        mutableStateOf(AnnotatedTextFormatter(note.text))
    }
    var isKeyboardVisible by remember { mutableStateOf(false) }

    val rootView = LocalView.current
    val density = LocalDensity.current.density
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val keyboardListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom
            isKeyboardVisible = keypadHeight > 100 * density
        }

        rootView.viewTreeObserver.addOnGlobalLayoutListener(keyboardListener)
        onDispose {
            rootView.viewTreeObserver.removeOnGlobalLayoutListener(keyboardListener)
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        BasicTextField(
            value = currentTextField,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newText ->
                currentTextField = newText
            },
            visualTransformation = AnnotatedTextTransformation(
                annotator,
                note,
                currentTextField
            )
        )
        if(isKeyboardVisible){
            KeyboardToolbar(annotator)
            Spacer(modifier = Modifier.height(currentDimensions().M))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewEditNote(){
    EditNote(Note(id=0, text= AnnotatedString("Test 123")))
}
