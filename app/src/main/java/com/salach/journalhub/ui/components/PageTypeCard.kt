package com.salach.journalhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography


@Composable
fun PageTypeCard(iconId: Int, text: String, description: String, onClick: () -> Unit) {
    val dimensions = currentDimensions()
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(dimensions.S + dimensions.M + dimensions.XL)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = description,
            contentScale = ContentScale.None,
            modifier = Modifier
                .padding(1.dp)
                .width(dimensions.L)
                .height(dimensions.L)
        )
        Text(
            text = text,
            style = currentTypography().L3R
        )
    }
}

@Preview
@Composable
fun PreviewPageTypeCard(){
    PageTypeCard(R.drawable.ic_file, "Note", "createNote"){}
}
