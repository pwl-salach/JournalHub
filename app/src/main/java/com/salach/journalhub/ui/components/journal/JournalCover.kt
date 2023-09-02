 package com.salach.journalhub.ui.components.journal

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

 @Composable
fun JournalCover(journal: Journal, updateTrigger: Boolean = false){
    val transition = updateTransition(targetState = journal.backgroundColor, label = "ColorTransition")
    val color by transition.animateColor(label = "CoverColor") { state ->
        Color(state)
    }
    val bookmarkExtraLength = currentDimensions().S
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(360.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = bookmarkExtraLength)
                .background(
                    color = color,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = currentDimensions().S,
                        bottomEnd = currentDimensions().S,
                        bottomStart = 0.dp
                    )
                )
        )
        Image(
            painter = painterResource(id = R.drawable.bookmark_85),
            contentDescription = "bookmark",
//            contentScale = ContentScale.None,
            modifier = Modifier
                .fillMaxHeight()
                .offset(x = currentDimensions().S)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(top = currentDimensions().S, bottom = currentDimensions().S + bookmarkExtraLength)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Top),
                ) {
                    Text(
                        text = journal.title,
                        style = currentTypography().T2R
                    )
                    Text(
                        text = journal.subtitle,
                        style = currentTypography().T2R.copy(color = ColorPalette.inkLight)
                    )
                }
                if (journal.icon != null){
                    Image(
                        painter = painterResource(id = journal.icon!!),
                        contentDescription = "image description",
                        colorFilter = ColorFilter.tint(Color(journal.iconColor)),
                        modifier = Modifier
                            .width(128.dp)
                            .height(128.dp)
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(currentDimensions().Half)
            ){
                Text(
                    text = if (journal.showCreatedDate && journal.createdDate != null) "Created: " + DateUtils.formatDate(journal.createdDate) else  "",
                    style = currentTypography().L3L.copy(color = ColorPalette.inkLight)
                )
                Text(
                    text = if (journal.showEditedDate && journal.editedDate != null) "Last edited: " + DateUtils.formatDate(journal.editedDate) else "",
                    style = currentTypography().L3L.copy(color = ColorPalette.inkLight)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFullJournalCover(){
    JournalCover(
        Journal(
            "My title",
            "New Subtitle",
            createdDate = LocalDate.of(2005, 4, 2),
            editedDate = LocalDate.of(2023, 6, 26),
            icon = R.drawable.ic_planetscale,
            showEditedDate = true
        )
    )
}


@Preview
@Composable
fun PreviewEmptyJournal(){
    JournalCover(Journal("", ""))
}

@Preview
@Composable
fun PreviewIconJournal(){
    JournalCover(Journal("", "", icon = R.drawable.ic_notebook ))
}
