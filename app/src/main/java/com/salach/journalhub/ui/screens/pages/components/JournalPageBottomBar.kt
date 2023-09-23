package com.salach.journalhub.ui.screens.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.salach.journalhub.R
import com.salach.journalhub.ui.components.IconButton
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography

@Composable
fun JournalPageBottomBar(
    currentIndex: Int,
    pagesCount: Int,
    viewPrevious: () -> Unit,
    viewNext: () -> Unit
) {
    if(pagesCount <= 1){ return }
    val dimensions = currentDimensions()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(dimensions.BottomBarHeight)
            .fillMaxWidth()
            .padding(dimensions.S)
    ) {
        Box(modifier = Modifier.width(dimensions.L))
        Text(
            text = "Page ${currentIndex + 1}/${pagesCount}",
            style = currentTypography().L3R
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(currentIndex != 0){
                IconButton(
                    iconId = R.drawable.ic_chevron_left,
                    description = "PreviousPage",
                    onClick = {
                        viewPrevious()
                    }
                )
            }
            if(currentIndex + 1 != pagesCount){
                IconButton(
                    iconId = R.drawable.ic_chevron_right,
                    description = "NextPage",
                    onClick = {
                        viewNext()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewJournalPageBottomBar(){
    Column {
        JournalPageBottomBar(1, 1, {}, {})
        JournalPageBottomBar(2, 9, {}, {})
        
    }
}