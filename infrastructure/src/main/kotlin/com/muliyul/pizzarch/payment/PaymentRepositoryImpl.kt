package com.muliyul.pizzarch.payment

import com.muliyul.pizzarch.domain.payment.*

class PaymentRepositoryImpl: PaymentRepository {

	override suspend fun findByIdOrNull(id: PaymentId): Payment? {
		TODO("Not yet implemented")
	}

	override suspend fun save(model: Payment): Payment {
		TODO("Not yet implemented")
	}

}
