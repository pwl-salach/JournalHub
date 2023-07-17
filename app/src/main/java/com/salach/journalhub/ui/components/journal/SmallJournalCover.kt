package com.salach.journalhub.ui.components.journal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.theme.Dimensions
import com.salach.journalhub.ui.theme.Typography

@Composable
fun SmallJournalCover(journal: Journal, hideTitle: Boolean = false) {
    Box(
        modifier = Modifier
            .width(48.dp)
            .height(72.dp)
            .background(
                color = Color(journal.backgroundColor),
                shape = RoundedCornerShape(
                    topStart = 0.dp, topEnd = Dimensions.Half,
                    bottomStart = 0.dp, bottomEnd = Dimensions.Half
                ),
            )
    ){
        Image(
            painter = painterResource(id = R.drawable.bookmark_85),
            contentDescription = "bookmark",
            modifier = Modifier
                .width(4.dp)
                .height(64.dp)
                .offset(x = Dimensions.Quarter)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimensions.Half, Alignment.Top),
            modifier = Modifier.fillMaxWidth().padding(top = Dimensions.Half)
        ) {
            Text(
                text = if (hideTitle) "" else journal.title ,
                style = Typography.L3L.copy(fontSize = 8.sp)
            )
            if(journal.icon != null){
                Image(
                    painter = painterResource(id = journal.icon!!),
                    contentDescription = "icon",
                    colorFilter = ColorFilter.tint(Color(journal.iconColor)),
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSmallJournalCover(){
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimensions.XS)
    ){
        SmallJournalCover(Journal("Qwe", "", icon = R.drawable.ic_apple))
        SmallJournalCover(Journal("Qwe", "", icon = R.drawable.ic_apple), hideTitle = true)
    }
}
