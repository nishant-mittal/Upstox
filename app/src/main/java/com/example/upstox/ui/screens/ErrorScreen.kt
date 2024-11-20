package com.example.upstox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.upstox.ui.theme.Background
import com.example.upstox.ui.theme.Poppins

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, errorText: String) {
    Column(
        modifier = modifier
            .background(Background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = errorText, style = Poppins.style(fontSize = 12))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(errorText = "Could not load data")
}