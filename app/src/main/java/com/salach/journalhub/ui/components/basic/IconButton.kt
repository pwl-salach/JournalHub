package com.salach.journalhub.ui.components.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.currentDimensions


@Composable
fun IconButton(
    iconId: Int, description: String,
    iconSize: Dp? = null, borderSize: Dp? = null,
    onClick: () -> Unit
) {
    val dimensions = currentDimensions()
    val iconSize = iconSize ?: dimensions.L
    val totalSize = iconSize + (borderSize ?: dimensions.S)
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(totalSize)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(iconSize)
                .padding(1.dp)  // FIXME really needed?
        ){
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = description
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIconButton(){
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            iconId = R.drawable.ic_sun,
            description = "Icon 0",
            onClick = {}
        )
        IconButton(
            iconId = R.drawable.ic_baguette,
            description = "Icon 1",
            onClick = {}
        )
    }
}
