package com.salach.journalhub.ui.screens.pages.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography


@Composable
fun JournalPageHeader(
    page: Page,
    paddingValues: PaddingValues
) {
    val dimensions = currentDimensions()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // TODO make it dependant on type of page
            Image(
                painter = painterResource(id = R.drawable.ic_file),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier
//                        .width(dimensions.XS)
//                        .height(dimensions.XS)
            )
            Text(
                text = page.type.name,
                style = currentTypography().B3R
            )
        }
        Text(
            text = "Created: 26/07/23, 15:36 ",
            style = currentTypography().B3R
        )
    }
}
