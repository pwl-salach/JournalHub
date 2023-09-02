package com.salach.journalhub.ui.components.journal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

@Composable
fun BigJournalCover(
    journal: Journal,
    onShowClicked: () -> Unit,
    onAddClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onRemoveClicked: () -> Unit,
) {
    Box(modifier = Modifier
//        .fillMaxWidth()
//        .fillMaxHeight()
        .width(380.dp)
        .height(600.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = currentDimensions().M)
                .background(
                    color = Color(journal.backgroundColor),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = currentDimensions().S,
                        bottomEnd = currentDimensions().S,
                        bottomStart = 0.dp
                    )
                )
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color(0x26000000),
                    ambientColor = Color(0x26000000)
                )
        )
        Image(
            painter = painterResource(id = R.drawable.bookmark_85),
            contentDescription = "bookmark",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .offset(currentDimensions().M)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = currentDimensions().L),
            ) {
                Text(
                    text = journal.title,
                    style = currentTypography().T2R.copy(fontSize = 62.sp)
                )
                Text(
                    text = journal.subtitle,
                    style = currentTypography().T2R.copy(fontSize = 30.sp, color = Color(0xFF464646))
                )
            }
            if (journal.icon != null){
                Image(
                    painter = painterResource(id = journal.icon!!),
                    contentDescription = "image description",
                    colorFilter = ColorFilter.tint(Color(journal.iconColor)),
                    modifier = Modifier
                        .width(256.dp)
                        .height(256.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(currentDimensions().Half),
                modifier = Modifier.padding(bottom = currentDimensions().L),
            ){

                Text(
                    text = if (journal.showCreatedDate && journal.createdDate != null) "Created: " + DateUtils.formatDate(journal.createdDate) else "",
                    style = currentTypography().L1L.copy(Color(0xFF414249))
                )

                Text(
                    text = if (journal.showEditedDate && journal.editedDate != null) "Last edited: " + DateUtils.formatDate(journal.editedDate) else "",
                    style = currentTypography().L1L.copy(Color(0xFF414249))
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = currentDimensions().XL, end = currentDimensions().S)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_book),
                    contentDescription = "Open",
                    modifier = Modifier
                        .width(currentDimensions().L)
                        .height(currentDimensions().L)
                        .clickable { onShowClicked() }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_file_plus),
                    contentDescription = "Add page",
                    modifier = Modifier
                        .width(currentDimensions().L)
                        .height(currentDimensions().L)
                        .clickable { onAddClicked() }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_pencil),
                    contentDescription = "Edit",
                    modifier = Modifier
                        .width(currentDimensions().L)
                        .height(currentDimensions().L)
                        .clickable { onEditClicked() }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = "Remove",
                    modifier = Modifier
                        .width(currentDimensions().L)
                        .height(currentDimensions().L)
                        .clickable { onRemoveClicked() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBigJournalCover(){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxHeight().fillMaxWidth()){
        BigJournalCover(
            Journal(
                "My title",
                "New Subtitle",
                createdDate = LocalDate.of(2005, 4, 2),
                editedDate = LocalDate.of(2023, 6, 26),
                icon = R.drawable.ic_planetscale,
                showEditedDate = true
            ), {}, {}, {}, {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyBigJournalCover(){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxHeight().fillMaxWidth()){
        BigJournalCover(
            Journal(
                "",
                "",
                icon = R.drawable.ic_planetscale,
            ), {}, {}, {}, {}
        )
    }
}
