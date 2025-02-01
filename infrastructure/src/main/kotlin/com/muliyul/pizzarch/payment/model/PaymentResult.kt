package com.muliyul.pizzarch.payment.model

import com.muliyul.pizzarch.domain.payment.*
import javax.money.*

data class PaymentResult(
	val id: PaymentId = PaymentId(),
	val amount: MonetaryAmount,
	val status: PaymentStatus = PaymentStatus.RECEIVED
)
