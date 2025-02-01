package com.muliyul.pizzarch.order

import com.muliyul.pizzarch.auth.*
import com.muliyul.pizzarch.domain.order.*
import com.muliyul.pizzarch.domain.usecase.order.*
import com.muliyul.pizzarch.order.api.*
import jakarta.inject.*

class OrderResource @Inject constructor(
	private val createOrderUseCase: CreateOrderUseCase,
) : OrderApi {
	override fun createOrder(request: CreateOrderRequest, user: User): OrderDto = TODO()
}
