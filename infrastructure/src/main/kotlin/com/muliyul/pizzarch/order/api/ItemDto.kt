package com.muliyul.pizzarch.order.api

import javax.money.*

data class ItemDto(
	val name: String,
	val description: String,
	val price: MonetaryAmount
)
