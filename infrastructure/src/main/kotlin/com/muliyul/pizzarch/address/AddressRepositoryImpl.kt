package com.muliyul.pizzarch.address

import com.muliyul.pizzarch.domain.address.*
import jakarta.inject.*
import kotlinx.coroutines.*

class AddressRepositoryImpl @Inject constructor(
	private val addressDao: AddressDao
) : AddressRepository {
	override suspend fun findByIdOrNull(id: AddressId): Address? = withContext(Dispatchers.IO) {
		val entity = async { addressDao.findByIdOrNull(id) }

		entity.await()?.let {
			Address(
				id = AddressId(it.externalId),
				street = it.street,
				city = it.city,
				zip = it.zip,
				country = it.country,
				instructions = it.instructions
			)
		}
	}

	override suspend fun save(model: Address): Address = withContext(Dispatchers.IO) {
		addressDao.save(model.toEntity())

		findById(model.id)
	}
}

private fun Address.toEntity(): AddressEntity {
	TODO("Not yet implemented")
}
