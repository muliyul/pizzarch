package com.muliyul.pizzarch.domain.item

import com.muliyul.pizzarch.domain.ddd.*
import javax.money.*

data class Item(
	override val id: ItemId,
	val name: String,
	val description: String,
	val price: MonetaryAmount
): Identifiable<ItemId>
