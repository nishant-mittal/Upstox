package com.example.upstox.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.upstox.R
import com.example.upstox.network.response.UserHoldingResponseDto
import com.example.upstox.ui.components.LineItem
import com.example.upstox.ui.components.UserHoldingItem
import com.example.upstox.ui.theme.Background
import com.example.upstox.ui.theme.Divider
import com.example.upstox.ui.theme.LossRed
import com.example.upstox.ui.theme.Poppins
import com.example.upstox.ui.theme.Primary
import com.example.upstox.ui.theme.ProfitGreen
import com.example.upstox.util.Util
import com.example.upstox.util.Util.formatIndianCurrencyWithSymbol

@Composable
fun UserHoldingScreen(modifier: Modifier = Modifier, data: UserHoldingResponseDto?) {
    val items = data?.userHolding
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Primary),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.portfolio),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                }
            }

            item {
                Text(
                    text = stringResource(id = R.string.holdings),
                    Modifier.padding(14.dp),
                    style = Poppins.style(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18
                    )
                )
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
            }

            itemsIndexed(items ?: emptyList()) { index, item ->
                UserHoldingItem(
                    data = item,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    shouldCardBeRoundedOnTop = index == 0,
                    shouldCardBeRoundedOnBottom = index == ((items?.size ?: 0) - 1)
                )
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        Card(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            border = BorderStroke(width = 1.dp, color = Divider),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    LineItem(
                        text1 = stringResource(id = R.string.current_value),
                        text2 = "${
                            Util.calculateCurrentValue(data?.userHolding)
                                ?.formatIndianCurrencyWithSymbol()
                        }"
                    )
                    LineItem(
                        text1 = stringResource(id = R.string.total_investment),
                        text2 = "${
                            Util.calculateTotalInvestment(data?.userHolding)
                                ?.formatIndianCurrencyWithSymbol()
                        }"
                    )
                    LineItem(
                        text1 = stringResource(id = R.string.todays_profit_and_loss),
                        text2 = "${
                            Util.calculateTodayPnl(data?.userHolding)
                                ?.formatIndianCurrencyWithSymbol()
                        }",
                        text2Color = if ((Util.calculateTodayPnl(data?.userHolding)
                                ?: 0.0) > 0.0
                        ) ProfitGreen else LossRed
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp,
                        top = if (isExpanded) 0.dp else 20.dp
                    ).clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        isExpanded = !isExpanded
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row() {
                    Text(
                        text = stringResource(id = R.string.profit_and_loss),
                        style = Poppins.style(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Icon(imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = "arrow up/down",
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${
                        Util.calculateTotalPnl(data?.userHolding)?.formatIndianCurrencyWithSymbol()
                    }",
                    style = Poppins.style(
                        color = if ((Util.calculateTotalPnl(data?.userHolding)
                                ?: 0.0) > 0.0
                        ) ProfitGreen else LossRed, fontSize = 14
                    )
                )
            }
        }
    }
}