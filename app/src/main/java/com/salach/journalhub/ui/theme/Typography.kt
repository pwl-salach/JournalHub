package com.salach.journalhub.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.salach.journalhub.R

//object Typography {
class AppTypography(private val scale: Float) {
    // Headline Bold
    val H1B = TextStyle(
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        color = ColorPalette.inkDark
    )
    val H2B = TextStyle(
        fontSize = 26.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        color = ColorPalette.inkDark
    )
    val H3B = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        color = ColorPalette.inkDark
    )
    // Headline Regular
    val H1R = TextStyle(
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        color = ColorPalette.inkDark
    )
    val H2R = TextStyle(
        fontSize = 26.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        color = ColorPalette.inkDark
    )
    val H3R = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        color = ColorPalette.inkDark
    )
    // Title Bold
    val T1B = TextStyle(
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        color = ColorPalette.inkDark
    )
    val T2B = TextStyle(
        fontSize = getFontSize(12.sp, 16.sp, 20.sp),
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.15.sp,
        color = ColorPalette.inkDark
    )
    val T3B = TextStyle(
        fontSize = 14 .sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.1.sp,
        color = ColorPalette.inkDark
    )
    // Title Regular
    val T1R = TextStyle(
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
//        lineHeight = 28.sp,
        color = ColorPalette.inkDark
    )
    val T2R = TextStyle(
        fontSize = getFontSize(12.sp, 16.sp, 20.sp),
        fontFamily = FontFamily(Font(R.font.roboto)),
        letterSpacing = 0.15.sp,
        color = ColorPalette.inkDark,
        lineHeight = 24.sp
    )
    val T3R = TextStyle(
        fontSize = 14 .sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        letterSpacing = 0.1.sp,
        color = ColorPalette.inkDark
    )
    // Label Bold
    val L1B = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.1.sp,
        color = ColorPalette.inkDark
    )
    val L2B = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    val L3B = TextStyle(
        fontSize = 11 .sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    // Label Regular
    val L1R = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        letterSpacing = 0.1.sp,
        color = ColorPalette.inkDark
    )
    val L2R = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    val L3R = TextStyle(
        fontSize = 11 .sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    // Label Light
    val L1L = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(300),
        letterSpacing = 0.1.sp,
        color = ColorPalette.inkDark
    )
    val L2L = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(300),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    val L3L = TextStyle(
        fontSize = 11.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(300),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    // Body Bold
    val B1B = TextStyle(
        fontSize = getFontSize(12.sp, 16.sp, 20.sp),
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    val B2B = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.25.sp,
        color = ColorPalette.inkDark
    )
    val B3B = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        letterSpacing = 0.4.sp,
        color = ColorPalette.inkDark
    )
    val B1R = TextStyle(
        fontSize = getFontSize(12.sp, 16.sp, 20.sp),
        fontFamily = FontFamily(Font(R.font.roboto)),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    val B2R = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        letterSpacing = 0.25.sp,
        color = ColorPalette.inkDark
    )
    val B3R = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.4.sp,
        color = ColorPalette.inkDark
    )
    val B1L = TextStyle(
        fontSize = getFontSize(12.sp, 16.sp, 20.sp),
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.5.sp,
        color = ColorPalette.inkDark
    )
    val B2L = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.25.sp,
        color = ColorPalette.inkDark
    )
    val B3L = TextStyle(
        fontSize = getFontSize(8.sp, 12.sp, 16.sp),
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.4.sp,
        color = ColorPalette.inkDark
    )

    private fun getFontSize(small: TextUnit, regular: TextUnit, big: TextUnit): TextUnit{
        return when {
            scale < 1.0f -> small
            scale > 1.5f -> big
            else -> regular
        }
    }
}

private val LocalAppTypography = compositionLocalOf {
    AppTypography(scale = 1.0f)
}

@Composable
fun ProvideAppTypography(scale: Float, content: @Composable () -> Unit) {
    val typography = AppTypography(scale)
    CompositionLocalProvider(LocalAppTypography provides typography, content = content)
}

@Composable
fun currentTypography(): AppTypography = LocalAppTypography.current
