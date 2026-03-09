package com.example.myapplication

import viewModels.CalculateViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import components.CalcDisplay
import components.CalcKeyboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateApp()
        }
    }
}


@Composable
fun CalculateApp() {
    val viewModel : CalculateViewModel = viewModel()
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        CalcDisplay(viewModel, Modifier.weight(1.5f))
        Box(modifier = Modifier.weight(1f)) {
            CalcKeyboard(viewModel)
        }
    }

}