package com.muliyul.pizzarch.order

import com.muliyul.pizzarch.domain.order.*
import org.jdbi.v3.sqlobject.kotlin.*
import org.jdbi.v3.sqlobject.statement.*
import ru.vyarus.guicey.jdbi3.installer.repository.*

@JdbiRepository
interface OrderDao {
	@SqlUpdate(
		"""
		REPLACE INTO orders (
		id,
		customer_id,
		total,
		currency,
		ordered_at,
		scheduled_at,
		received_at,
		payment_id,
		status,
		delivery_address
		) VALUES (
		:id,
		:customerId,
		:total,
		:currency,
		:orderedAt,
		:scheduledAt,
		:receivedAt,
		:paymentId,
		:status,
		:deliveryAddress
		) 
		"""
	)
	fun save(@BindKotlin order: OrderEntity)

	@SqlQuery(
		"""
		SELECT * FROM orders WHERE id = :orderId
		"""
	)
	fun findByIdOrNull(orderId: OrderId): OrderEntity?
}
