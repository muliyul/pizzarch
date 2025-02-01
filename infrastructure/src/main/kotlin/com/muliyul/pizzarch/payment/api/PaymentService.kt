package com.muliyul.pizzarch.payment.api

import com.muliyul.pizzarch.domain.order.*
import com.muliyul.pizzarch.domain.payment.*
import com.muliyul.pizzarch.payment.model.*

interface PaymentService {
	suspend fun pay(order: Order, paymentMethod: PaymentMethod): PaymentResult
}
