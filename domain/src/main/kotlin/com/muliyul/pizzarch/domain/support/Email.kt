package com.muliyul.pizzarch.domain.support

@JvmInline
value class Email(
	val value: String
) {

	init {
		require(regex.matches(value)) { "Invalid email address: $value" }
	}
}

private val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
