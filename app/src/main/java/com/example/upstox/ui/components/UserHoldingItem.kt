package com.example.upstox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.upstox.network.response.UserHoldingDto
import com.example.upstox.ui.theme.LossRed
import com.example.upstox.ui.theme.Poppins
import com.example.upstox.ui.theme.ProfitGreen
import com.example.upstox.ui.theme.TertiaryText
import com.example.upstox.util.Util
import com.example.upstox.util.Util.formatIndianCurrencyWithSymbol

@Composable
fun UserHoldingItem(
    modifier: Modifier = Modifier,
    data: UserHoldingDto?,
    shouldCardBeRoundedOnTop: Boolean = false,
    shouldCardBeRoundedOnBottom: Boolean = false
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = if (shouldCardBeRoundedOnTop) RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ) else if (shouldCardBeRoundedOnBottom) RoundedCornerShape(
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ) else RectangleShape,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Card(shape = CircleShape, modifier = Modifier.size(48.dp)) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(brush = Brush.linearGradient(Util.generateRandomGradientColors()))
                ) {
                    if (data?.symbol?.isNullOrEmpty() == false) {
                        Text(
                            text = Util.getFirstThreeLetters(data?.symbol ?: "").uppercase(),
                            style = Poppins.style(
                                color = Color.White,
                                fontSize = 12,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.Start) {
                if (data?.symbol?.isNullOrEmpty() == false) {
                    Text(
                        text = data?.symbol,
                        style = Poppins.style(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                if (data?.quantity != null) {
                    Text(
                        text = "NET QTY: ${data?.quantity}",
                        style = Poppins.style(color = TertiaryText, fontSize = 12)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                if (data?.ltp != null) {
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = TertiaryText,
                                fontFamily = Poppins.fontFamily,
                                fontSize = 10.sp
                            )
                        ) {
                            append("LTP: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontFamily = Poppins.fontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("${data?.ltp?.formatIndianCurrencyWithSymbol()}")
                        }
                    })
                    Spacer(modifier = Modifier.height(4.dp))
                }
                if (Util.calculateTotalPnl(data) != null) {
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = TertiaryText,
                                fontFamily = Poppins.fontFamily,
                                fontSize = 10.sp
                            )
                        ) {
                            append("P&L: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = if ((Util.calculateTotalPnl(data)
                                        ?: 0.0) > 0.0
                                ) ProfitGreen else LossRed,
                                fontFamily = Poppins.fontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append(
                                "${
                                    Util.calculateTotalPnl(data)?.formatIndianCurrencyWithSymbol()
                                }"
                            )
                        }
                    })
                }
            }
        }
        Divider(
            thickness = 1.dp,
            color = com.example.upstox.ui.theme.Divider,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}