package com.muliyul.pizzarch.domain.customer

import com.muliyul.pizzarch.domain.ddd.*
import com.muliyul.pizzarch.domain.support.*

interface CustomerRepository : Repository<CustomerId, Customer> {
	suspend fun findByEmailOrNull(email: Email): Customer?
}

suspend fun CustomerRepository.findById(customerId: CustomerId): Customer =
	findByIdOrNull(customerId) ?: error("Customer $customerId not found")
