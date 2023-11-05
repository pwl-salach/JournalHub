package com.salach.journalhub.ui.components.journal

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

@Composable
fun JournalCardWIthPreview(
    journal: Journal,
    onOpenClicked: () -> Unit,
    onShowClicked: () -> Unit,
    onAddClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onRemoveClicked: () -> Unit,
) {
    var showOptions by remember { mutableStateOf(false) }
    val dimensions = currentDimensions()
    val typography = currentTypography()
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onOpenClicked() }
    ){
        SmallJournalCover(journal, hideTitle = true)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = ColorPalette.primarySurface1,
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .padding(dimensions.XS)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = journal.title,
                        style = typography.L1R
                    )
                    Text(
                        text = journal.subtitle,
                        style = typography.L1R.copy(color = Color(0xFF464646))
                    )
                }
                Column {
                    if (journal.showCreatedDate && journal.createdDate != null) {
                        Text(
                            text = "Created: " + DateUtils.formatDate(journal.createdDate),
                            style = typography.L3L.copy(fontSize = 8.sp)
                        )
                    }
                    if (journal.showEditedDate && journal.editedDate != null) {
                        Text(
                            text = "Updated: " + DateUtils.formatDate(journal.editedDate),
                            style = typography.L3L.copy(fontSize = 8.sp)
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.animateContentSize()
            ) {
                if (showOptions){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = "ShowOptions",
                        Modifier.clickable {
                            showOptions = !showOptions
                        }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "Remove",
                        modifier = Modifier
                            .width(dimensions.M)
                            .height(dimensions.M)
                            .clickable { onRemoveClicked() }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pencil),
                        contentDescription = "Edit",
                        modifier = Modifier
                            .width(dimensions.M)
                            .height(dimensions.M)
                            .clickable { onEditClicked() }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_file_plus),
                        contentDescription = "Add page",
                        modifier = Modifier
                            .width(dimensions.M)
                            .height(dimensions.M)
                            .clickable { onAddClicked() }
                    )

                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = "ShowOptions",
                        Modifier.clickable {
                            showOptions = !showOptions
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewJournalCardWIthPreview(){
    JournalCardWIthPreview(
        Journal("Title", "Subtitle", createdDate = LocalDate.now()),
        {}, {}, {}, {}, {}
    )
}
