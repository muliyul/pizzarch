package com.muliyul.pizzarch.domain.order

import java.util.*

@JvmInline
value class OrderId(val value: UUID = UUID.randomUUID())
