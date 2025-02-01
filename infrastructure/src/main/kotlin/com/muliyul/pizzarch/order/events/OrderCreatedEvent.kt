package com.muliyul.pizzarch.order.events

import com.muliyul.pizzarch.domain.order.*

data class OrderCreatedEvent(val order: Order)
