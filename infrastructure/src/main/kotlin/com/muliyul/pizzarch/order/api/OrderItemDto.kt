package com.muliyul.pizzarch.order.api

import com.muliyul.pizzarch.domain.item.*

data class OrderItemDto(
	val itemId: ItemId,
	val quantity: Int
)
