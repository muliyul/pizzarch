package com.muliyul.pizzarch.customer

import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.support.*
import jakarta.inject.*
import kotlinx.coroutines.*


class CustomerRepositoryImpl @Inject constructor(
	private val customerDao: CustomerDao
) : CustomerRepository {
	override suspend fun findByIdOrNull(id: CustomerId): Customer? = withContext(Dispatchers.IO) {
		val entity = async { customerDao.findByIdOrNull(id) }

		entity.await()?.let {
			Customer(
				id = CustomerId(it.externalId),
				email = Email(it.email)
			)
		}
	}

	override suspend fun findByEmailOrNull(email: Email): Customer? = withContext(Dispatchers.IO) {
		val entity = async { customerDao.findByEmailOrNull(email) }

		entity.await()?.let {
			Customer(
				id = CustomerId(it.externalId),
				email = Email(it.email)
			)
		}
	}

	override suspend fun save(model: Customer): Customer = withContext(Dispatchers.IO) {
		val entity = model.toEntity()
		customerDao.save(entity)

		Customer(
			id = CustomerId(entity.externalId),
			email = Email(entity.email)
		)
	}

}

fun Customer.toEntity(): CustomerEntity = CustomerEntity(
	externalId = id.value,
	email = email.value
)
