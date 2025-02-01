package com.muliyul.pizzarch.address

import java.util.UUID

data class AddressEntity(
	val externalId: UUID,
	val street: String,
	val city: String,
	val zip: String,
	val country: String,
	val instructions: String?
)
