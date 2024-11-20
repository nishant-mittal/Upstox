package com.example.upstox

import com.example.upstox.network.response.UserHoldingDto
import com.example.upstox.util.Util
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculationsTest {
    @Test
    fun `current value test`() {
        val holding = UserHoldingDto(
            symbol = "MAHABANK",
            quantity = 990,
            ltp = 38.05,
            avgPrice = 35.0,
            close = 40.0
        )
        assertEquals(37669.5, Util.calculateCurrentValue(holding), 0.01)
    }

    @Test
    fun `current value as a list test`() {
        val holdings = listOf(
            UserHoldingDto(
                symbol = "MAHABANK",
                quantity = 990,
                ltp = 38.05,
                avgPrice = 35.0,
                close = 40.0
            ),
            UserHoldingDto(
                symbol = "ICICI",
                quantity = 100,
                ltp = 118.25,
                avgPrice = 110.0,
                close = 105.0
            )
        )
        Util.calculateCurrentValue(holdings)?.let {
            assertEquals(49494.5, it, 0.01)
        }
    }

    @Test
    fun `total investment test`() {
        val holding = UserHoldingDto(
            symbol = "MAHABANK",
            quantity = 990,
            ltp = 38.05,
            avgPrice = 35.0,
            close = 40.0
        )
        assertEquals(34650.0, Util.calculateTotalInvestment(holding), 0.01)
    }

    @Test
    fun `total investment as a list test`() {
        val holdings = listOf(
            UserHoldingDto(
                symbol = "MAHABANK",
                quantity = 990,
                ltp = 38.05,
                avgPrice = 35.0,
                close = 40.0
            ),
            UserHoldingDto(
                symbol = "ICICI",
                quantity = 100,
                ltp = 118.25,
                avgPrice = 110.0,
                close = 105.0
            )
        )
        Util.calculateTotalInvestment(holdings)?.let {
            assertEquals(45650.0, it, 0.01)
        }
    }

    @Test
    fun `total PNL test`() {
        val holding = UserHoldingDto(
            symbol = "MAHABANK",
            quantity = 990,
            ltp = 38.05,
            avgPrice = 35.0,
            close = 40.0
        )
        val pnl = Util.calculateCurrentValue(holding) - Util.calculateTotalInvestment(holding)
        assertEquals(3019.5, pnl, 0.01)
    }

    @Test
    fun `total PNL as a list test`() {
        val holdings = listOf(
            UserHoldingDto(
                symbol = "MAHABANK",
                quantity = 990,
                ltp = 38.05,
                avgPrice = 35.0,
                close = 40.0
            ),
            UserHoldingDto(
                symbol = "ICICI",
                quantity = 100,
                ltp = 118.25,
                avgPrice = 110.0,
                close = 105.0
            )
        )
        val pnl = (Util.calculateCurrentValue(holdings) ?: 0.0) - (Util.calculateTotalInvestment(holdings) ?: 0.0)
        assertEquals(3844.5, pnl, 0.01)
    }

    @Test
    fun `today's PNL test`() {
        val holding = UserHoldingDto(
            symbol = "MAHABANK",
            quantity = 990,
            ltp = 38.05,
            avgPrice = 35.0,
            close = 40.0
        )
        assertEquals(1930.5, Util.calculateTodayPnl(holding), 0.01)
    }

    @Test
    fun `today's PNL as a list test`() {
        val holdings = listOf(
            UserHoldingDto(
                symbol = "MAHABANK",
                quantity = 990,
                ltp = 38.05,
                avgPrice = 35.0,
                close = 40.0
            ),
            UserHoldingDto(
                symbol = "ICICI",
                quantity = 100,
                ltp = 118.25,
                avgPrice = 110.0,
                close = 105.0
            )
        )
        Util.calculateTodayPnl(holdings)?.let {
            assertEquals(605.5, it, 0.01)
        }
    }
}