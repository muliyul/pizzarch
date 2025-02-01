package com.muliyul.pizzarch.address

import com.muliyul.pizzarch.domain.address.*
import java.time.*

interface TimezoneResolver {
	suspend fun getTimezone(address: Address): ZoneOffset
}
