package com.ajudaqui.fornecedor.dto

data class OrderDTO(val orderId: String, val itens: List<Item> = emptyList())

data class Item(
        val name: String,
        val brand: String,
        val quantity: Double,
)
