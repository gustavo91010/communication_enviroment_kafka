package com.ajudaqui.fornecedor.service

import com.ajudaqui.fornecedor.dto.BudgetDTO
import com.ajudaqui.fornecedor.dto.BudgetItemDTO
import com.ajudaqui.fornecedor.kafka.entity.Order
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Random
import org.springframework.stereotype.Service

@Service
class FinancialService() {

  fun generatedBudget(order: Order): BudgetDTO {
    val unitPrice = calculateUnitPrice()
    val budgetItems =
            order.items.map { i ->
              BudgetItemDTO(
                      i.name.toString(),
                      i.brand.toString(),
                      i.quantity,
                      unitPrice,
                      unitPrice.multiply(BigDecimal(i.quantity))
              )
            }
    return BudgetDTO(order.code.toString(), budgetItems)
  }

  fun calculateUnitPrice(): BigDecimal {
    val min = BigDecimal(10)
    val max = BigDecimal(100)

    val random = Random()
    val range = max.subtract(min)
    val randomValue = random.nextDouble() * range.toDouble() + min.toDouble()
    return BigDecimal(randomValue).setScale(2, RoundingMode.HALF_UP)
  }
}
