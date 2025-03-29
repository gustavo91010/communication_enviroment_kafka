package com.ajudaqui.fornecedor.service

import com.ajudaqui.fornecedor.kafka.entity.Order
import org.springframework.stereotype.Service

@Service
class OrderService(private val financialService: FinancialService) {

  fun opa(order: Order) {
    print("convertendo na minha classe: ${financialService.generatedBudget(order)}")
  }
}
