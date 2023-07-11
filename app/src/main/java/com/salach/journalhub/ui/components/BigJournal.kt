package com.salach.journalhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

@Composable
fun BigJournal(journal: Journal){
    Box {
        Box(
            Modifier
                .offset(x = 0.dp, y = 0.dp)
                .width(224.dp)
                .height(352.dp)
                .background(
                    color = Color(journal.backgroundColor),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 0.dp
                    )
                )
        )
        Image(
            painter = painterResource(id = R.drawable.bookmark_85),
            contentDescription = "image description",
            contentScale = ContentScale.None,
            modifier = Modifier
                .offset(x = 14.14727.dp, y = 0.dp)
                .width(16.dp)
                .height(368.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = journal.title,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                letterSpacing = 0.22.sp,
                modifier = Modifier
                    .offset(x = 69.dp, y = 70.dp)
                //            .width(80.dp)
                //                .height(28.dp)
            )
            Text(
                text = journal.subtitle,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                color = Color(0xFF464646),
                textAlign = TextAlign.Center,
                letterSpacing = 0.14.sp,
                modifier = Modifier
                    .offset(x = 65.dp, y = 74.dp)
                //                    .width(81.dp)
                //                    .height(16.dp)
            )
            if (journal.icon != null){
                Image(
                    painter = painterResource(id = journal.icon),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .offset(x = 48.dp, y = 80.dp)
                        .width(128.dp)
                        .height(128.dp)
                )
            }
            if (journal.showCreatedDate && journal.createdDate != null){
                Text(
                    text = "Created: " + DateUtils.formatDate(journal.createdDate),
                    fontSize = 8.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    color = Color(0xFF464646),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.08.sp,
                    modifier = Modifier
                        .offset(x = 70.dp, y = 135.dp)
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
                        .offset(x = 64.dp, y = 139.dp)
                        //                .width(89.dp)
                        .height(10.dp)
                )
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
            icon = R.drawable.ic_planetscale
        )
    )
}


@Preview
@Composable
fun PreviewEmptyBigJournal(){
    BigJournal(Journal("", ""))
}
