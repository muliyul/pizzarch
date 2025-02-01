package com.muliyul.pizzarch.domain.usecase.order

import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.order.*

data class CreateOrderInput(
	val request: CreateOrderRequest,
	val customerId: CustomerId
)
