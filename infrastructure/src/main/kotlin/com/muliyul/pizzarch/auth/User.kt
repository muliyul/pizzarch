package com.muliyul.pizzarch.auth

import com.muliyul.pizzarch.domain.support.*
import java.security.Principal

data class User(
	val id: UserId,
	val email: Email,
): Principal {
	override fun getName(): String  = id.toString()
}
