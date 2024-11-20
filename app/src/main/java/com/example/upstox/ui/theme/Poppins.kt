package com.example.upstox.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.upstox.R

object Poppins {
    val fontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
    )

    fun style(
        color: Color = Color.Black,
        fontSize: Int = 10,
        fontWeight: FontWeight = FontWeight.Normal
    ) = TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        color = color,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
}