package components

import viewModels.CalculateViewModel
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CalcButtonStyle(
    val containerColor: Color,
    val contentColor: Color,
    val fontSize: TextUnit = 32.sp,
    val shape: Shape = CircleShape
)

data class ButtonData (
    val label: String,
    val style: CalcButtonStyle,
    val action: (CalculateViewModel) -> Unit
)

object CalcButtonThemes {
    val Number = CalcButtonStyle(
        containerColor = Color(0xFF333333),
        contentColor = Color.White
    )

    val Action = CalcButtonStyle(
        fontSize = 24.sp,
        containerColor = Color(0xFFA6A6A6),
        contentColor = Color.Black
    )

    val Operator = CalcButtonStyle(
        containerColor = Color(0xFFFF9F0A),
        contentColor = Color.White
    )

    val ZeroNumber = CalcButtonStyle(
        containerColor = Color(0xFF333333),
        contentColor = Color.White,
        shape = RoundedCornerShape(100.dp)
    )
}



@Composable
fun CalcKeyboard(
    calcViewModel: CalculateViewModel,
) {

    val buttons = listOf(
        ButtonData("C", CalcButtonThemes.Action) { it.onClearClick() },
        ButtonData("Del", CalcButtonThemes.Action) { /* Logic đảo dấu */ },
        ButtonData("%", CalcButtonThemes.Action) { /* Logic % */ },
        ButtonData("÷", CalcButtonThemes.Operator) { it.onOperatorClick("÷") },

        ButtonData("7", CalcButtonThemes.Number) { it.onDigitClick("7") },
        ButtonData("8", CalcButtonThemes.Number) { it.onDigitClick("8") },
        ButtonData("9", CalcButtonThemes.Number) { it.onDigitClick("9") },
        ButtonData("×", CalcButtonThemes.Operator) { it.onOperatorClick("×") },

        ButtonData("4", CalcButtonThemes.Number) { it.onDigitClick("4") },
        ButtonData("5", CalcButtonThemes.Number) { it.onDigitClick("5") },
        ButtonData("6", CalcButtonThemes.Number) { it.onDigitClick("6") },
        ButtonData("-", CalcButtonThemes.Operator) { it.onOperatorClick("-") },

        ButtonData("1", CalcButtonThemes.Number) { it.onDigitClick("1") },
        ButtonData("2", CalcButtonThemes.Number) { it.onDigitClick("2") },
        ButtonData("3", CalcButtonThemes.Number) { it.onDigitClick("3") },
        ButtonData("+", CalcButtonThemes.Operator) { it.onOperatorClick("+") },
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        userScrollEnabled = false
    ) {
        items(buttons) { btn ->
            CalcButton(
                label = btn.label,
                style = btn.style,
                onClick = { btn.action(calcViewModel) }
            )
        }

        item(span = { GridItemSpan(2) }) { // Chiếm 2 cột
            CalcButton(
                label = "0",
                style = CalcButtonThemes.ZeroNumber,
                onClick = { calcViewModel.onDigitClick("0") }
            )
        }
        item {
            CalcButton(
                label = ".",
                style = CalcButtonThemes.Number,
                onClick = { calcViewModel.onDigitClick(".") }
            )
        }
        item {
            CalcButton(
                label = "=",
                style = CalcButtonThemes.Operator,
                onClick = { calcViewModel.onEqualsClick() }
            )
        }
    }
}

@Composable
fun CalcButton(
    label: String,
    style: CalcButtonStyle,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(4.dp),
        shape = style.shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = style.containerColor,
            contentColor = style.contentColor
        )
    ) {
        Text(
            text = label,
            fontSize = style.fontSize
        )

    }
}

