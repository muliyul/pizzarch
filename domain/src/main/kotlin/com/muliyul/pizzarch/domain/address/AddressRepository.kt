package com.muliyul.pizzarch.domain.address

import com.muliyul.pizzarch.domain.ddd.*

interface AddressRepository : Repository<AddressId, Address> {
}


suspend inline fun <ID, reified MODEL : Identifiable<ID>> Repository<ID, MODEL>.findById(id: ID) =
	findByIdOrNull(id) ?: error("${MODEL::class} not found")
