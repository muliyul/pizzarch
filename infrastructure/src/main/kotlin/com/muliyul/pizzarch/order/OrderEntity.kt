package com.muliyul.pizzarch.order

import com.muliyul.pizzarch.domain.address.*
import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.order.*
import com.muliyul.pizzarch.domain.payment.*
import java.time.*
import java.util.*
import javax.money.*

data class OrderEntity(
	val externalId: UUID,
	val customerId: CustomerId,
	val total: MonetaryAmount,
	val orderedAt: Instant,
	val scheduledFor: Instant,
	val receivedAt: Instant?,
	val paymentId: PaymentId,
	val status: OrderStatus,
	val addressId: AddressId
)
