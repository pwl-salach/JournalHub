package com.salach.journalhub.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getScreenScalingFactor(): Float {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenHeightDp = displayMetrics.heightPixels / displayMetrics.density
    return screenHeightDp / 800
}
