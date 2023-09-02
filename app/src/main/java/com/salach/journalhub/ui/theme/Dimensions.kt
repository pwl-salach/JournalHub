package com.salach.journalhub.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(val scale: Float) {
    val BottomBarHeight = getDimension(60.dp, 80.dp, 100.dp)
    val Quarter = getDimension(1.dp, 2.dp, 4.dp)
    val Half = getDimension(2.dp, 4.dp, 8.dp)
    val XS = getDimension(6.dp, 8.dp, 16.dp)
    val S = getDimension(12.dp, 16.dp, 32.dp)
    val M = getDimension(18.dp, 24.dp, 48.dp)
    val L = getDimension(24.dp, 32.dp, 64.dp)
    val XL = getDimension(36.dp, 48.dp, 80.dp)

    private fun getDimension(small: Dp, regular: Dp, big: Dp): Dp {
        return when {
            scale < 1.0f -> small
            scale > 1.5f -> big
            else -> regular
        }
    }
}

private val LocalAppDimensions = compositionLocalOf {
    Dimensions(scale = 1.0f)
}

@Composable
fun ProvideAppDimensions(scale: Float, content: @Composable () -> Unit) {
    val dimensions = Dimensions(scale)
    CompositionLocalProvider(LocalAppDimensions provides dimensions, content = content)
}

@Composable
fun currentDimensions(): Dimensions = LocalAppDimensions.current
