package com.ajudaqui.fornecedor.service

import com.ajudaqui.fornecedor.dto.BudgetDTO
import com.ajudaqui.fornecedor.dto.BudgetItemDTO
import com.ajudaqui.fornecedor.dto.OrderDTO
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Random
import org.springframework.stereotype.Service

@Service
// class FinancialService(private val producer: ProducerService) {
class FinancialService() {
  fun opa() = "opa!"
  fun producerBudget(order: OrderDTO) {

    // producer.sendBudgetRequest(777L, generatedBudget(order))
  }
  fun generatedBudget(order: OrderDTO): BudgetDTO {
    val unitPrice = calculateUnitPrice()
    val budgetItems =
            order.itens.map { i ->
              BudgetItemDTO(
                      i.name,
                      i.brand,
                      i.quantity,
                      unitPrice,
                      unitPrice.multiply(BigDecimal(i.quantity)).setScale(2, RoundingMode.CEILING)
              )
            }
    return BudgetDTO(order.orderId, budgetItems)
  }

  private fun calculateUnitPrice(): BigDecimal {
    val min = BigDecimal(10)
    val max = BigDecimal(100)

    val random = Random()
    val range = max.subtract(min)
    val randomValue = random.nextDouble() * range.toDouble() + min.toDouble()
    return BigDecimal(randomValue).setScale(2, RoundingMode.HALF_UP)
  }
}
