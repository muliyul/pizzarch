package com.muliyul.pizzarch.domain.stub

import com.muliyul.pizzarch.domain.address.*
import com.muliyul.pizzarch.domain.annotations.*

@DomainStub
class AddressRepositoryStub: AddressRepository {
	override suspend fun findByIdOrNull(id: AddressId): Address? {
		TODO("Not yet implemented")
	}

	override suspend fun save(model: Address): Address {
		TODO("Not yet implemented")
	}
}
