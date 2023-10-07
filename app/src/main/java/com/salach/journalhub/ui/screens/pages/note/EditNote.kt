package com.salach.journalhub.ui.screens.pages.note

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.ui.components.KeyboardToolbar
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditNote(note: MutableState<Note>) {
    val text = remember { mutableStateOf(AnnotatedString(note.value.text)) }
    var isKeyboardVisible = remember { mutableStateOf(false) }

    val rootView = LocalView.current
    val density = LocalDensity.current.density
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val keyboardListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom
            isKeyboardVisible.value = keypadHeight > 100 * density
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
        TextField(
            value = text.value.text,
            textStyle = currentTypography().B1B,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isKeyboardVisible.value = focusState.isFocused
                },
            onValueChange = {
                val annotatedText = applyAnnotations(it)
                text.value = annotatedText
                note.value.text = text.value.text
            },
        )
        if(isKeyboardVisible.value){
            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ){
                KeyboardToolbar()
            }
            Spacer(modifier = Modifier.height(currentDimensions().M))
        }
    }
}

fun applyAnnotations(input: String): AnnotatedString {
    val builder = AnnotatedString.Builder()

    var currentIndex = 0
    while (currentIndex < input.length) {
        val nextIndex = input.indexOf("BOLD", currentIndex, ignoreCase = true)
        if (nextIndex == -1) {
            builder.append(
                input.substring(currentIndex, input.length),
            )
            break
        } else {
            builder.withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
            ) {
                append(AnnotatedString(input.substring(currentIndex, nextIndex + 4)))
            }
            currentIndex = nextIndex + 4
        }
    }
    return builder.toAnnotatedString()
}

//@Preview
//@Composable
//fun PreviewEditNote(){
//    EditNote()
//}