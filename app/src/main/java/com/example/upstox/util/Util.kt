package com.example.upstox.util

import androidx.compose.ui.graphics.Color
import com.example.upstox.network.response.UserHoldingDto
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.random.Random

object Util {
    fun generateRandomGradientColors(): List<Color> {
        val random = Random.Default

        val numberOfColors = random.nextInt(2, 5)
        return List(numberOfColors) {
            Color(
                red = random.nextFloat(),
                green = random.nextFloat(),
                blue = random.nextFloat(),
                alpha = 1f
            )
        }
    }

    fun getFirstThreeLetters(word: String): String {
        return if (word.length >= 3) {
            word.substring(0, 3)
        } else {
            word
        }
    }

    fun calculateCurrentValue(holding: UserHoldingDto?): Double {
        val quantity = holding?.quantity ?: 0
        val ltp = holding?.ltp ?: 0.0
        return ltp * quantity
    }

    fun calculateCurrentValue(holdings: List<UserHoldingDto?>?): Double? {
        return holdings?.sumOf {
            calculateCurrentValue(it)
        }
    }

    fun calculateTotalInvestment(holding: UserHoldingDto?): Double {
        val quantity = holding?.quantity ?: 0
        val averagePrice = holding?.avgPrice ?: 0.0
        return averagePrice * quantity
    }

    fun calculateTotalInvestment(holdings: List<UserHoldingDto?>?): Double? {
        return holdings?.sumOf {
            calculateTotalInvestment(it)
        }
    }

    fun calculateTotalPnl(holding: UserHoldingDto?): Double? {
        val currentValue = calculateCurrentValue(holding)
        val totalInvestment = calculateTotalInvestment(holding)
        return currentValue - totalInvestment
    }

    fun calculateTotalPnl(holdings: List<UserHoldingDto?>?): Double? {
        val currentValue = calculateCurrentValue(holdings)
        val totalInvestment = calculateTotalInvestment(holdings)
        if (currentValue == null || totalInvestment == null) {
            return null;
        }
        return currentValue - totalInvestment
    }

    fun calculateTodayPnl(holding: UserHoldingDto?): Double {
        val quantity = holding?.quantity ?: 0
        val close = holding?.close ?: 0.0
        val ltp = holding?.ltp ?: 0.0
        return (close - ltp) * quantity
    }

    fun calculateTodayPnl(holdings: List<UserHoldingDto?>?): Double? {
        return holdings?.sumOf {
            calculateTodayPnl(it)
        }
    }

    fun Double.formatIndianCurrencyWithSymbol(): String {
        val symbols = DecimalFormatSymbols(Locale.ENGLISH).apply {
            groupingSeparator = ','
        }
        val indianFormat = DecimalFormat("₹##,##,##,###.##", symbols)
        return indianFormat.format(this)
    }
}