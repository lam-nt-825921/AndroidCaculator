package components

import viewModels.CalculateViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


object CalcDisplayThemes {
    val Background = Color.Black
    val PrimaryText = Color.White
    val PrimaryFontSize = 32.sp
    val SecondaryText = Color(0xFFA6A6A6)
    val SecondaryFontSize = 24.sp
}

data class CalcTextTheme(
    val color : Color,
    val fontSize : TextUnit
)

fun getCalcDisplayThemes(isResult: Boolean, isCalculated: Boolean): CalcTextTheme {
    if (isResult) {
        return if (isCalculated) {
            CalcTextTheme(CalcDisplayThemes.PrimaryText, CalcDisplayThemes.PrimaryFontSize)
        } else {
            CalcTextTheme(CalcDisplayThemes.SecondaryText, CalcDisplayThemes.SecondaryFontSize)
        }
    }
    return if (isCalculated) {
        CalcTextTheme(CalcDisplayThemes.PrimaryText, CalcDisplayThemes.SecondaryFontSize)
    }
    else {
        CalcTextTheme(CalcDisplayThemes.PrimaryText, CalcDisplayThemes.PrimaryFontSize)
    }

}


@Composable
fun CalcDisplay(
    viewModel: CalculateViewModel,
    modifier: Modifier = Modifier
) {
    val calcExpression : CalcTextTheme = getCalcDisplayThemes(false, viewModel.isCalculated.value)
    val calcResult : CalcTextTheme = getCalcDisplayThemes(true, viewModel.isCalculated.value)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CalcDisplayThemes.Background)
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = viewModel.historyText.value,
                color = CalcDisplayThemes.SecondaryText,
                fontSize = CalcDisplayThemes.SecondaryFontSize
            )
        }
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = viewModel.expressionText.value,
                    color = calcExpression.color,
                    fontSize = calcExpression.fontSize
                )
                Text(
                    text = viewModel.resultText.value,
                    color = calcResult.color,
                    fontSize = calcResult.fontSize
                )
            }

        }


    }
}


