package com.muliyul.pizzarch.domain.payment

import com.muliyul.pizzarch.domain.ddd.*
import javax.money.*

data class Payment(
	override val id: PaymentId = PaymentId(),
	val amount: MonetaryAmount,
	val method: PaymentMethod
) : Identifiable<PaymentId>
