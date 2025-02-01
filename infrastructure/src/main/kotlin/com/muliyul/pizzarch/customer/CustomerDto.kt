package com.muliyul.pizzarch.customer

import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.support.*

data class CustomerDto(
	val id: CustomerId,
	val email: Email,
)
