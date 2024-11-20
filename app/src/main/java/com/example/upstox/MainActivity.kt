package com.example.upstox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import com.example.upstox.ui.viewmodel.UserHoldingViewModel
import com.example.upstox.ui.models.UiState
import com.example.upstox.ui.screens.ErrorScreen
import com.example.upstox.ui.screens.LoadingScreen
import com.example.upstox.ui.screens.UserHoldingScreen
import com.example.upstox.ui.theme.Background
import com.example.upstox.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    private val viewModel: UserHoldingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetSystemBarsColor()
            val uiState = viewModel.userHoldingData.observeAsState()

            when (uiState.value) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Error -> ErrorScreen(errorText = (uiState.value as UiState.Error).errorText)
                is UiState.Success -> UserHoldingScreen(data = (uiState.value as? UiState.Success)?.data)
                else -> {}
            }
        }
    }
}

@Composable
fun SetSystemBarsColor() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        try {
            systemUiController.setStatusBarColor(Primary)
            systemUiController.setNavigationBarColor(Background)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}