package com.muliyul.pizzarch.payment.event

import com.muliyul.pizzarch.domain.order.*
import com.muliyul.pizzarch.domain.payment.*
import com.muliyul.pizzarch.payment.model.*

data class PaymentReceivedEvent(
	val orderId: OrderId,
	val paymentId: PaymentId
)
