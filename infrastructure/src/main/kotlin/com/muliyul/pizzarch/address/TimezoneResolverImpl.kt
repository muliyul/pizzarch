package com.muliyul.pizzarch.address

import com.muliyul.pizzarch.domain.address.*
import kotlinx.coroutines.*
import java.time.*

class TimezoneResolverImpl : TimezoneResolver {
	override suspend fun getTimezone(address: Address): ZoneOffset = withContext(Dispatchers.IO) {
		ZoneOffset.UTC
	}
}
