package com.salach.journalhub.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

@Composable
fun BigJournal(journal: Journal, updateTrigger: Boolean = false){
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
                .padding(bottom = Dimensions.s)
                .background(
                    color = color,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = Dimensions.s,
                        bottomEnd = Dimensions.s,
                        bottomStart = 0.dp
                    )
                )
        )
        Image(
            painter = painterResource(id = R.drawable.bookmark_85),
            contentDescription = "bookmark",
//            contentScale = ContentScale.None,
            modifier = Modifier
                .offset(x = Dimensions.s, y = 0.dp)
                .fillMaxHeight()
                .height(360.dp)
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
                modifier = Modifier.padding(top = Dimensions.l),
            ) {
                Text(
                    text = journal.title,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    letterSpacing = 0.22.sp,
                )
                Text(
                    text = journal.subtitle,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    color = Color(0xFF464646),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.14.sp,
                )
            }
            if (journal.icon != null){
                Image(
                    painter = painterResource(id = journal.icon!!),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    colorFilter = ColorFilter.tint(Color(journal.iconColor)),
                    modifier = Modifier
                        .width(128.dp)
                        .height(128.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.half),
                modifier = Modifier.padding(bottom = Dimensions.m),
            ){
                if (journal.showCreatedDate && journal.createdDate != null){
                    Text(
                        text = "Created: " + DateUtils.formatDate(journal.createdDate),
                        fontSize = 8.sp,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        color = Color(0xFF464646),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.08.sp,
                        modifier = Modifier
//                        .offset(x = 70.dp, y = 135.dp)
                            //                .width(77.dp)
                            .height(10.dp)
                    )
                }
                if (journal.showEditedDate && journal.editedDate != null){
                    Text(
                        text = "Last edited: " + DateUtils.formatDate(journal.editedDate),
                        fontSize = 8.sp,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        color = Color(0xFF464646),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.08.sp,
                        modifier = Modifier
//                        .offset(x = 64.dp, y = 139.dp)
                            //                .width(89.dp)
                            .height(10.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFullBigJournal(){
    BigJournal(
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
    BigJournal(Journal("", ""))
}
