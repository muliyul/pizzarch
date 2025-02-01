package com.muliyul.pizzarch.domain.order

import com.muliyul.pizzarch.domain.address.*
import com.muliyul.pizzarch.domain.item.LineItem
import java.time.*

data class CreateOrderRequest(
	val items: List<LineItem>,
	val addressId: AddressId,
	val scheduledFor: LocalDateTime
)

