package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateApp()
        }
    }
}

class caculateViewModel : ViewModel() {
}
@Composable
fun CalculateApp() {


}

@Preview
@Composable
fun CalculatorButtonPreview() {
    CalculatorButton(symbol = "7", color = Color.White, contentColor = Color.Black)
}

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    color: Color,
    contentColor: Color,
    onClick: () -> Unit = {}
) {

}

@Composable
fun DisplayArea(
    expression: String,
    result: String,
    isEvaluated: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        if (isEvaluated) {
            // Trạng thái đã tính toán xong
            CalculatorText(text = expression, isPrimary = false)
            Spacer(modifier = Modifier.height(8.dp))
            CalculatorText(text = "= $result", isPrimary = true)
        } else {
            // Trạng thái đang nhập
            CalculatorText(text = expression, isPrimary = true)
            if (result.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                // Kết quả ước tính: mờ và nhỏ hơn
                CalculatorText(text = result, isPrimary = false)
            }
        }
    }
}

@Composable
fun CalculatorText(
    text: String,
    isPrimary: Boolean
) {
    Text(
        text = text,
        fontSize = if (isPrimary) 72.sp else 32.sp,
        color = if (isPrimary) Color.White else Color.White.copy(alpha = 0.4f), // Dùng alpha để mờ
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.End,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculateAppPreview() {
    CalculateApp()
}
