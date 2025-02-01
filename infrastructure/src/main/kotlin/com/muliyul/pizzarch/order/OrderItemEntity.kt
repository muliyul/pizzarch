package com.muliyul.pizzarch.order

import java.util.*

data class OrderItemEntity(
	val itemId: UUID,
	val orderId: UUID,
	val quantity: Int,
)
