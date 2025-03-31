package com.ajudaqui.fornecedor.service

import com.ajudaqui.fornecedor.dto.Item
import com.ajudaqui.fornecedor.dto.OrderDTO
import java.util.UUID
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class FinancialServiceTest() {
  private val itemDTO = listOf(Item("pao", "plus vita", 3.5), Item("queijo", "parmesão", 1.5))
  private val orderDTO = OrderDTO(UUID.randomUUID().toString(), itemDTO)
  private var financialService = FinancialService()

  // @Test
  fun `Deve dar um opa!`() {

    assertThat(financialService.opa()).isEqualTo("opa!")
  }

  @Test
  fun `deve gerar um budget`() {

    val budgetDTO = financialService.generatedBudget(orderDTO)

    assertThat(budgetDTO.budgetItens[0].quantity).isEqualTo(3.5)
  }

  @Test
  fun `deve ter 2 casas decimais`() {

    val budgetDTO = financialService.generatedBudget(orderDTO)

    var totalPrice = budgetDTO.budgetItens[0].totalPrice.toPlainString().split(".")[1].length
    var unitPrice = budgetDTO.budgetItens[0].unitPrice.toPlainString().split(".")[1].length

    assertThat(totalPrice).isEqualTo(2)
    assertThat(unitPrice).isEqualTo(2)
  }
}
