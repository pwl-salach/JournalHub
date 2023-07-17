package com.salach.journalhub.ui.components.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.salach.journalhub.ui.theme.Dimensions
import com.salach.journalhub.ui.theme.Typography
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

@Composable
fun JournalCardWIthPreview(
    journal: Journal,
    onShowClicked: () -> Unit,
    onAddClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onRemoveClicked: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
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
                .padding(Dimensions.XS)
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
                        style = Typography.L1R
                    )
                    Text(
                        text = journal.subtitle,
                        style = Typography.L1R.copy(color = Color(0xFF464646))
                    )
                }
                Column() {
                    if (journal.showCreatedDate && journal.createdDate != null) {
                        Text(
                            text = "Created: " + DateUtils.formatDate(journal.createdDate),
                            style = Typography.L3L.copy(fontSize = 8.sp)
                        )
                    }
                    if (journal.showEditedDate && journal.editedDate != null) {
                        Text(
                            text = "Updated: " + DateUtils.formatDate(journal.editedDate),
                            style = Typography.L3L.copy(fontSize = 8.sp)
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimensions.S)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(Dimensions.S, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_book),
                        contentDescription = "Open",
                        modifier = Modifier
                            .width(Dimensions.M)
                            .height(Dimensions.M)
                            .clickable { onShowClicked() }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_file_plus),
                        contentDescription = "Add page",
                        modifier = Modifier
                            .width(Dimensions.M)
                            .height(Dimensions.M)
                            .clickable { onAddClicked() }
                    )

                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(Dimensions.S, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pencil),
                        contentDescription = "Edit",
                        modifier = Modifier
                            .width(Dimensions.M)
                            .height(Dimensions.M)
                            .clickable { onEditClicked() }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "Remove",
                        modifier = Modifier
                            .width(Dimensions.M)
                            .height(Dimensions.M)
                            .clickable { onRemoveClicked() }
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
        {}, {}, {}, {}
    )
}
