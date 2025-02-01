package com.muliyul.pizzarch.domain.order

import com.muliyul.pizzarch.domain.ddd.*

interface OrderRepository: Repository<OrderId, Order> {
}
