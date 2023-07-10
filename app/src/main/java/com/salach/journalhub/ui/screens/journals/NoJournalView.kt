package com.salach.journalhub.ui.screens.journals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.BigJournal
import com.salach.journalhub.ui.theme.ColorPalette
import java.time.LocalDate

@Composable
fun NoJournalView(navController: NavController, viewModel: JournalsViewModel) {
    val navigateToJournals = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.scale(1f)){
            BigJournal(icon = R.drawable.ic_planetscale)
        }
        Text(
            text = """
                |You haven’t created any journals yet.
                |Would you like to do it now?
            """.trimMargin(),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            letterSpacing = 0.14.sp,
//            modifier = Modifier
//                .offset(x = 88.dp, y = 540.dp)
//                .width(236.dp)
//                .height(32.dp)
        )
        Box(modifier = Modifier
//            .offset(x = 100.dp, y = 604.dp)
            .shadow(elevation = 4.dp, spotColor = Color(0x26000000), ambientColor = Color(0x26000000))
            .width(212.dp)
            .height(56.dp)
            .background(color = Color(0xFFD8BFD8), shape = RoundedCornerShape(size = 4.dp))
            .padding(start = 40.dp, top = 16.dp, end = 40.dp, bottom = 16.dp)
        ){
            Text(
                text = "Let’s START!",
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight(900),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                letterSpacing = 0.22.sp,

                modifier = Modifier.clickable {
                    viewModel.addJournal(
                        Journal("Title", "Test",
                            R.drawable.ic_planetscale, ColorPalette.FrenchGray40.toArgb(), ColorPalette.Leaf70.toArgb(),
                            LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
                        )
                    )
                    viewModel.addJournal(
                        Journal("Title", "Test",
                            R.drawable.ic_planetscale, ColorPalette.AlertsNeutral50.toArgb(), ColorPalette.Lavender50.toArgb()    ,
                            LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
                        )
                    )
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


//@Preview
//@Composable
//fun PreviewNoJournalView(){
//    NoJournalView(rememberNavController())
//}
