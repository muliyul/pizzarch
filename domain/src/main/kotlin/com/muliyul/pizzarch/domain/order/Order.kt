package com.muliyul.pizzarch.domain.order

import com.muliyul.pizzarch.domain.address.*
import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.item.*
import com.muliyul.pizzarch.domain.payment.*
import java.time.*

data class Order(
	val id: OrderId = OrderId(),
	val customerId: CustomerId,
	val items: List<LineItem>,
	val status: OrderStatus = OrderStatus.PLACED,
	val addressId: AddressId,
	val orderedAt: ZonedDateTime,
	val scheduledFor: ZonedDateTime,
	val receivedAt: ZonedDateTime? = null,
	val paymentId: PaymentId
)
