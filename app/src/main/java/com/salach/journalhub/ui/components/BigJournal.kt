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
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

@Composable
fun BigJournal(journal: Journal){
    BigJournal(journal.title, journal.subtitle, journal.icon, journal.createdDate, journal.editedDate)
}

@Composable
fun BigJournal(
        title: String? = null, subtitle: String? = null, icon: Int? = null, createdDate: LocalDate? = null, lastEdited: LocalDate? = null
) {
    Box {
        Box(
            Modifier
                .offset(x = 0.dp, y = 0.dp)
                .width(224.dp)
                .height(352.dp)
                .background(
                    color = Color(0xFFD6D6D6),
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
            if(title != null){
                Text(
                    text = title,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    letterSpacing = 0.22.sp,
                    modifier = Modifier
                        .offset(x = 69.dp, y = 70.dp)
                    //            .width(80.dp)
                    //                .height(28.dp)
                )
            }
            if(subtitle != null){
                Text(
                    text = subtitle,
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
            }
            if (icon != null){
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .offset(x = 48.dp, y = 80.dp)
                        .width(128.dp)
                        .height(128.dp)
                )
            }
            if (createdDate != null){
                Text(
                    text = "Created: " + DateUtils.formatDate(createdDate),
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
            if (lastEdited != null){
                Text(
                    text = "Last edited: " + DateUtils.formatDate(lastEdited),
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


@Preview
@Composable
fun PreviewFullBigJournal(){
    BigJournal(
        "My title",
        "New Subtitle",
        R.drawable.ic_planetscale,
        LocalDate.of(2005, 4, 2),
        LocalDate.of(2023, 6, 26)
    )
}


@Preview
@Composable
fun PreviewEmptyBigJournal(){
    BigJournal()
}
