package com.salach.journalhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.enums.PageType
import com.salach.journalhub.enums.getPageTypeIcon
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.utils.DateUtils
import java.time.LocalDate

@Composable
fun PageMiniature(
    page: Page,
    isSelectable: Boolean,
    onClick: () -> Unit
) {
    val dimensions = currentDimensions()
    val isSelected = remember { mutableStateOf(false) }
//    val typography = currentTypography()
    val titleColor = if (isSelectable && isSelected.value) ColorPalette.primary else ColorPalette.NeutralBlack
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.Half, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .width(dimensions.XL + dimensions.L + dimensions.XS)
            .height(dimensions.XL + dimensions.XL + dimensions.XL + dimensions.XS)
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .shadow(
                    elevation = dimensions.Quarter,
                    spotColor = Color(0x26000000),
                    ambientColor = Color(0x26000000)
                )
                .border(
                    width = 1.dp,
                    color = ColorPalette.outline,
                    shape = RoundedCornerShape(size = dimensions.Half)
                )
                .background(
                    color = ColorPalette.NeutralWhite,
                    shape = RoundedCornerShape(size = dimensions.Half)
                )
                .padding(dimensions.Half)
                .fillMaxWidth()
                .height(dimensions.XL + dimensions.XL + dimensions.S)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxHeight()
            ) {
                Checkbox(
                    checked = isSelected.value,
                    onCheckedChange = {},
                    modifier = Modifier
                        .size(dimensions.M)
                        .alpha(if (isSelectable) 1.0f else 0.0f)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .size(dimensions.M)
                        .background(
                            color = ColorPalette.Lavender10,
                            shape = RoundedCornerShape(size = dimensions.Quarter)
                        )
                        .padding(dimensions.Half)
                ) {
                    Image(
                        painter = getPageTypeIcon(pageType = page.type),
                        contentDescription = "image description",
                        modifier = Modifier
                            .size(dimensions.S)
//                            .padding(1.dp)
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensions.Half, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = page.title,
                style = TextStyle(
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(700),
                    color = titleColor,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.5.sp,
                )
            )
            Text(
                text = DateUtils.formatDate(page.editedDate),
                style = TextStyle(
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(400),
                    color = ColorPalette.outline,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.5.sp,
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewPageMiniature(){
    val page = Page(0, "My Long Title", LocalDate.now(), PageType.NOTE)
    Column {
        PageMiniature(page, true, {})
        PageMiniature(page, false, {})
    }
}