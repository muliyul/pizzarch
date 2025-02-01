package com.muliyul.pizzarch.domain.customer

import com.muliyul.pizzarch.domain.ddd.*
import com.muliyul.pizzarch.domain.support.*

data class Customer(
	override val id: CustomerId = CustomerId(),
	val email: Email
): Identifiable<CustomerId>
