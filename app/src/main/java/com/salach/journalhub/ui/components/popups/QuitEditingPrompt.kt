package com.salach.journalhub.ui.components.popups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions
import com.salach.journalhub.ui.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuitEditingPrompt(
    openPopup: Boolean,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    if(openPopup){
        ModalBottomSheetLayout(
            sheetShape = RoundedCornerShape(size = Dimensions.XS),
            sheetBackgroundColor = ColorPalette.primarySurface5,
            sheetContent = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(Dimensions.S)


                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_alert_hexagon),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(ColorPalette.AlertsNegative50)
                        )
                        Text(
                            text = "Exit to Journal overview page?",
                            style = Typography.T2B
                        )
                    }
                    Text(
                        text = "Progress will be lost.",
                        style = Typography.B3R,
                        modifier = Modifier.padding(horizontal = Dimensions.L)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                4.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(Dimensions.S)
                                .clickable { onCancel() }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_x),
                                contentDescription = null,
                            )
                            Text(
                                text = "Cancel",
                                style = Typography.L2R
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                4.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { onConfirm() }
                                .border(
                                    width = 1.dp, color = ColorPalette.AlertsNegative50,
                                    shape = RoundedCornerShape(size = 4.dp)
                                )
                                .padding(horizontal = Dimensions.S, vertical = Dimensions.XS)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_arrow_back),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(ColorPalette.AlertsNegative50)
                            )
                            Text(
                                text = "Exit",
                                style = Typography.L2R.copy(color = ColorPalette.AlertsNegative50)
                            )
                        }
                    }
                }
            },
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded),
        ) {
        }
    }
}

@Preview
@Composable
fun PreviewQuitEditingPrompt(){
    QuitEditingPrompt(true, {}, {})
}