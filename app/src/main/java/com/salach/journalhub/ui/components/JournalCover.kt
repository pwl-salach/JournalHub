package com.salach.journalhub.ui.components

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
import com.salach.journalhub.ui.theme.Dimensions
import com.salach.journalhub.ui.theme.Typography
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

@Composable
fun JournalCover(journal: Journal, updateTrigger: Boolean = false){
    val transition = updateTransition(targetState = journal.backgroundColor, label = "ColorTransition")
    val color by transition.animateColor(label = "CoverColor") { state ->
        Color(state)
    }
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(360.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = Dimensions.S)
                .background(
                    color = color,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = Dimensions.S,
                        bottomEnd = Dimensions.S,
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
                .offset(x = Dimensions.S)
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
                modifier = Modifier.padding(top = Dimensions.L),
            ) {
                Text(
                    text = journal.title,
                    style = Typography.T2R
                )
                Text(
                    text = journal.subtitle,
                    style = Typography.T2R.copy(color = Color(0xFF464646))
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.Half),
                modifier = Modifier.padding(bottom = Dimensions.M),
            ){
                if (journal.showCreatedDate && journal.createdDate != null){
                    Text(
                        text = "Created: " + DateUtils.formatDate(journal.createdDate),
                        style = Typography.L3L.copy(Color(0xFF414249))
                    )
                }
                if (journal.showEditedDate && journal.editedDate != null){
                    Text(
                        text = "Last edited: " + DateUtils.formatDate(journal.editedDate),
                        style = Typography.L3L.copy(Color(0xFF414249))
                    )
                }
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
fun PreviewEmptyBigJournal(){
    JournalCover(Journal("", ""))
}
