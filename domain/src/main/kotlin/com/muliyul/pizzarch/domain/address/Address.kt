package com.muliyul.pizzarch.domain.address

import com.muliyul.pizzarch.domain.ddd.*

data class Address(
	override val id: AddressId = AddressId(),
	var street: String,
	var city: String,
	var zip: String,
	var country: String,
	var instructions: String?
) : Identifiable<AddressId>
