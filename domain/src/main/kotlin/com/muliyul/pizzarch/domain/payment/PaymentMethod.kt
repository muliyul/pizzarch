package com.muliyul.pizzarch.domain.payment

sealed interface PaymentMethod {
	data class CreditCard(val number: String, val expiry: String, val cvv: String) : PaymentMethod
	data object Cash : PaymentMethod
}
