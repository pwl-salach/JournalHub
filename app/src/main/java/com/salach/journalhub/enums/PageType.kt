package com.salach.journalhub.enums

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.salach.journalhub.R

enum class PageType{
    NOTE,
    TASK_LIST,
    SHOPPING_LIST,
    GOAL,
    ACTIVITY_PLAN
}

fun getPageTypeIconId(pageType: PageType): Int {
    return when (pageType) {
        PageType.NOTE -> R.drawable.ic_notes
        PageType.TASK_LIST -> R.drawable.ic_checkup_list
        PageType.SHOPPING_LIST -> R.drawable.ic_shopping_cart
        PageType.GOAL -> R.drawable.ic_target_arrow
        PageType.ACTIVITY_PLAN -> R.drawable.ic_run
    }
}

@Composable
fun getPageTypeIcon(pageType: PageType): Painter {
    return painterResource(id = getPageTypeIconId(pageType))
}

@Composable
fun getPageTypeString(pageType: PageType): String {
    return when (pageType) {
        PageType.NOTE -> stringResource(id = R.string.note)
        PageType.TASK_LIST -> stringResource(id = R.string.task_list)
        PageType.SHOPPING_LIST -> stringResource(id = R.string.shopping_list)
        PageType.GOAL -> stringResource(id = R.string.goal)
        PageType.ACTIVITY_PLAN -> stringResource(id = R.string.activity_plan)
    }
}
