package com.example.upstox.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.upstox.ui.theme.Poppins
import com.example.upstox.ui.theme.SecondaryText

@Composable
fun LineItem(
    modifier: Modifier = Modifier,
    text1: String? = null,
    text2: String? = null,
    text2Color: Color = SecondaryText
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (text1?.isNullOrEmpty() == false) {
            Text(text = text1, style = Poppins.style(color = SecondaryText, fontSize = 14))
        }
        if (text2?.isNullOrEmpty() == false) {
            Text(text = text2, style = Poppins.style(color = text2Color, fontSize = 14))
        }
    }
}