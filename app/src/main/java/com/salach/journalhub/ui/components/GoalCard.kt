package com.salach.journalhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Goal
import com.salach.journalhub.ui.theme.Dimensions


@Composable
fun GoalCard(icon: Int, title: String, subtitle: String, progress: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(all = Dimensions.xs)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Color(0xfffafafa))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    modifier = Modifier.size(size = Dimensions.L)
                )
                Spacer(
                    modifier = Modifier.width(width = Dimensions.XS)
                )
                Column {
                    Text(
                        text = title,
                        color = Color(0xff272830),
                        lineHeight = 1.14.em,
                        style = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = 0.1.sp
                        )
                    )
                    Spacer(
                        modifier = Modifier.height(height = Dimensions.Half)
                    )
                    Text(
                        text = subtitle,
                        color = Color(0xff464646),
                        lineHeight = 1.2.em,
                        style = TextStyle(
                            fontSize = 10.sp,
                            letterSpacing = 0.1.sp
                        )
                    )
                }
            }
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = "Remove",
                        modifier = Modifier
//                            .offset(x = 0.dp, y = 0.dp)
                            .width(Dimensions.L)
                            .height(Dimensions.L)
                    )
                    Spacer(
                        modifier = Modifier.width(width = Dimensions.XS)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add",
                        modifier = Modifier
//                            .offset(x = 0.dp, y = 0.dp)
                            .width(Dimensions.L)
                            .height(Dimensions.L)
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(height = Dimensions.XS)
        )
        Progress(progress)
    }
}


@Preview
@Composable
fun PreviewGoalTracker() {
    GoalCard(
        R.drawable.ic_launcher_foreground,
        "Drink water",
        "250ml cups",
        0.30f
    )
}


@Composable
fun CurrentGoals(goals: LiveData<List<Goal>>) {
    val itemsState by goals.observeAsState(emptyList())

    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(Dimensions.XS, Alignment.CenterVertically),
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ){
        itemsIndexed(itemsState) { _, goal ->
            GoalCard(goal.icon, goal.title, goal.subtitle, goal.progress)
        }
    }
}

@Preview
@Composable
fun PreviewCurrentGoals() {
    val previewData = MutableLiveData<List<Goal>>()
    previewData.value = listOf(
        Goal(R.drawable.tabler_icon_bottle, "Test1", "small test", 0.52f),
        Goal(R.drawable.icon_sunrise, "Test2", "small test", 0.78f)
    )
    CurrentGoals(previewData)
}
