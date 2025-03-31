package com.ajudaqui.fornecedor.service

import com.ajudaqui.fornecedor.dto.Item
import com.ajudaqui.fornecedor.dto.OrderDTO
import kafka.entity.Order
import org.springframework.stereotype.Service

@Service
class OrderService(private val financialService: FinancialService) {

  fun tratandoOrder(order: OrderDTO) {}

  fun mapOrder(order: Order): OrderDTO {
    val itemDTO = order.items.map { i -> Item(i.name.toString(), i.brand.toString(), i.quantity) }
    return OrderDTO(order.code.toString(), itemDTO)
  }
}
