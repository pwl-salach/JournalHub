package com.salach.journalhub.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputLine(
    value: String, textStyle: TextStyle, placeholder: String = "",
    modifier: Modifier, onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
){
    val interactionSource = remember { MutableInteractionSource() }
    val enabled = true
    val singleLine = true

    val colors = TextFieldDefaults.textFieldColors()

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = ColorPalette.primarySurface2,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .indicatorLine(
                enabled = enabled,
                isError = false,
                interactionSource = interactionSource,
                colors = colors
            ),
        keyboardOptions = keyboardOptions,
        textStyle = textStyle,
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = value,
            innerTextField = it,
            singleLine = singleLine,
            enabled = enabled,
            placeholder = {
                Text(placeholder, style = textStyle.copy(color = ColorPalette.inkLight))
            },
            interactionSource = interactionSource,
            visualTransformation = PasswordVisualTransformation(),
            contentPadding = PaddingValues(
                vertical = currentDimensions().Half,
                horizontal = currentDimensions().XS
            )
        )
    }
}

@Preview
@Composable
fun PreviewInputLine(){
    Column {
        InputLine("DDD", currentTypography().T2R, "", Modifier, {})
        InputLine("",  currentTypography().T2R, "Insert text", Modifier, {})
    }
}
