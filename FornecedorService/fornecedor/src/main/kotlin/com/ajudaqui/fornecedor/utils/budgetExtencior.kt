package com.ajudaqui.fornecedor.utils

import com.ajudaqui.fornecedor.dto.BudgetDTO
import java.math.RoundingMode
import java.time.LocalDateTime
import kafka.entity.BudgetItem
import kafka.entity.BudgetRequest

fun BudgetDTO.mapToBudgetKafka(): BudgetRequest {
  val budgetItensDTO = this.budgetItens
  val budgetItens =
          Array<BudgetItem>(this.budgetItens.size) { index ->
            val unitPrice =
                    budgetItensDTO[index].unitPrice.setScale(2, RoundingMode.CEILING).toDouble()
            val totalPrice =
                    budgetItensDTO[index].totalPrice.setScale(2, RoundingMode.CEILING).toDouble()

            BudgetItem.newBuilder()
                    .setName(budgetItensDTO[index].name)
                    .setBrand(budgetItensDTO[index].brand)
                    .setQuantity(budgetItensDTO[index].quantity)
                    .setUnitPrice(unitPrice)
                    .setTotalPrice(totalPrice)
                    .build()
          }

  println(
          budgetItens.toList()
  ) // Verifique os itens aqui para garantir que os valores estão corretos
  val totalItemPrice = budgetItens.sumOf { it.totalPrice }
  return BudgetRequest.newBuilder()
          .setCode(this.orderId)
          .setItems(budgetItens.toList())
          .setTotalPrice(totalItemPrice)
          .setStatus("em analise")
          .setTimestamp(LocalDateTime.now().toString())
          .build()
}
