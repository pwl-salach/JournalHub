package com.salach.journalhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Schedule
import com.salach.journalhub.enums.TimeUnit
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun UpcomingSchedule(){

}

@Composable
fun MyScheduleCard(scheduledTime: LocalTime, currentTime: LocalTime, icon: Int,
                   title: String, subtitle: String, isDone: Boolean){
    Row(
        horizontalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(380.dp)
            .height(40.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset(x = 0.dp, y = 0.dp)
                .width(56.dp)
                .height(40.dp)
                .background(
                    color = ColorPalette.ThistleThistle100,
                    shape = RoundedCornerShape(size = currentDimensions().Half)
                )
                .padding(currentDimensions().XS)
        ) {
            Text(
                text = "07:30",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                color = Color(  0xFF464646),
                textAlign = TextAlign.Center,
                letterSpacing = 0.1.sp,
                modifier = Modifier
//                    .offset(x = 8.dp, y = 12.dp)
                    .width(40.dp)
                    .height(currentDimensions().S)
            )
        }
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            modifier = Modifier
                .width(currentDimensions().L)
                .height(currentDimensions().L)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = ColorPalette.ThistleThistle100,
                    shape = RoundedCornerShape(size = currentDimensions().Half)
                )
                .width(260.dp)
                .height(40.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = currentDimensions().Half))
                .padding(vertical = currentDimensions().Half, horizontal = currentDimensions().XS)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(currentDimensions().Half, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .width(54.dp)
                    .height(currentDimensions().L)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    color = Color(0xFF464646),
//                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier
//                        .width(54.dp)
                        .height(currentDimensions().S)
                )
                Text(
                    text = subtitle,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    color = Color(0xFF464646),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier
//                        .offset(x = 0.dp, y = 20.dp)
//                        .width(44.dp)
                        .height(12.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.unchecked_square),
                contentDescription = "icon",
                modifier = Modifier
//                    .offset(x = 228.dp, y = 8.dp)
                    .width(currentDimensions().M)
                    .height(currentDimensions().M)
            )
        }
    }
}

@Preview
@Composable
fun PreviewMyScheduleCard(){
    MyScheduleCard(
        LocalTime.now(),
        LocalTime.now(),
        R.drawable.icon_sunrise,
        "Wake up",
        "At sunrise",
        false
    )
}

@Composable
fun MySchedule(schedules: LiveData<List<Schedule>>){
    val itemsState by schedules.observeAsState(emptyList())
    val now = LocalTime.now()
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(currentDimensions().XS, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .offset(x = 0.dp, y = 344.dp)
//            .width(412.dp)
//            .height(544.dp)
//            .padding(start = currentDimensions().s, end = currentDimensions().s)
    ){
        itemsIndexed(itemsState) { _, schedule ->
            MyScheduleCard(
                scheduledTime = schedule.time,
                currentTime = now,
                icon = R.drawable.icon_sunrise,
                title = "Test",
                subtitle = "Test2",
                isDone = true
            )
        }
    }
}

@Preview
@Composable
fun PreviewMySchedule(){
    val previewData = MutableLiveData<List<Schedule>>()
    previewData.value = listOf(
        Schedule(0, TimeUnit.DAYS, LocalDate.now(), null, LocalTime.now(), null),
        Schedule(0, TimeUnit.DAYS, LocalDate.now(), null, LocalTime.now(), null),
        Schedule(0, TimeUnit.DAYS, LocalDate.now(), null, LocalTime.now(), null),
    )
    MySchedule(schedules = previewData)
}
