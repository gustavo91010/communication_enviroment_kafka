package com.ajudaqui.fornecedor.dto

import java.math.BigDecimal

data class BudgetDTO(val orderId: String, val budgetItens: List<BudgetItemDTO> = emptyList())

data class BudgetItemDTO(
        val name: String,
        val brand: String,
        val quantity: Double,
        val unitPrice: BigDecimal,
        val totalPrice: BigDecimal,
)
