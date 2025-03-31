package com.ajudaqui.fornecedor.kafka

import com.ajudaqui.fornecedor.dto.BudgetDTO
import kafka.entity.BudgetItem
import kafka.entity.BudgetRequest
import org.springframework.stereotype.Service

@Service
class Mapper() {
// converter esse map em  **Função de Extensão** depois..
  // fun mapToBudgetKafka(budgetDTO: BudgetDTO): BudgetRequest {
  //   val budgetItensDTO = budgetDTO.budgetItens
  //   val budgetItens =
  //           Array<BudgetItem>(budgetDTO.budgetItens.size) { index ->
  //             BudgetItem.newBuilder()
  //                     .setName(budgetItensDTO[index].name)
  //                     .setBrand(budgetItensDTO[index].brand)
  //                     .setQuantity(budgetItensDTO[index].quantity)
  //                     .setUnitPrice(budgetItensDTO[index].unitPrice.toDouble())
  //                     .setTotalPrice(budgetItensDTO[index].totalPrice.toDouble())
  //                     .build()
  //           }
  //   return BudgetRequest.newBuilder()
  //           .setCode(budgetDTO.orderId)
  //           .setItems(budgetItens.toList())
  //           .build()
  // }
}
