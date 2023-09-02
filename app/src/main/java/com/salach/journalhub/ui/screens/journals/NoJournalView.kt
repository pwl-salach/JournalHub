package com.salach.journalhub.ui.screens.journals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.journal.JournalCover
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography

@Composable
fun NoJournalView(
    navController: NavController,
//    viewModel: JournalsViewModel
) {
    val navigateToJournals = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.scale(1f)){
            JournalCover(Journal("", "", icon = R.drawable.ic_planetscale))
        }
        Text(
            text = """
                |You haven’t created any journals yet.
                |Would you like to do it now?
            """.trimMargin(),
            style = currentTypography().T2R,
            textAlign = TextAlign.Center,
        )
        Box(modifier = Modifier
//            .offset(x = 100.dp, y = 604.dp)
            .shadow(elevation = currentDimensions().Half, spotColor = Color(0x26000000), ambientColor = Color(0x26000000))
            .background(color = ColorPalette.primary, shape = RoundedCornerShape(size = currentDimensions().Half))
            .padding(vertical = currentDimensions().S, horizontal = currentDimensions().XL)
        ){
            Text(
                text = "Let’s START!",
                style = currentTypography().L2R,
//                color = Color(0xFFFFFFFF),
//                textAlign = TextAlign.Center,

                modifier = Modifier.clickable {
//                    viewModel.addJournal(
//                        Journal("Title", "Test",
//                            R.drawable.ic_planetscale, ColorPalette.FrenchGray40.toArgb(), ColorPalette.Leaf70.toArgb(),
//                            LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
//                        )
//                    )
//                    viewModel.addJournal(
//                        Journal("Title", "Test",
//                            R.drawable.ic_planetscale, ColorPalette.AlertsNeutral50.toArgb(), ColorPalette.Lavender50.toArgb()    ,
//                            LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
//                        )
//                    )
                    navigateToJournals.value = true
                }
            )
        }
    }
    LaunchedEffect(navigateToJournals.value) {
        if (navigateToJournals.value) {
            navController.navigate("journals"){
                popUpTo("dashboard") { inclusive = true }
            }
        }
    }
}


@Preview
@Composable
fun PreviewNoJournalView(){
    CompositionLocalProvider(LocalViewModel provides provideViewModelForPreview()) {
        NoJournalView(rememberNavController())
    }
}
