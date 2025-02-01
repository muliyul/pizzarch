package com.muliyul.pizzarch.order

import com.muliyul.pizzarch.domain.order.*
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import ru.vyarus.guicey.jdbi3.installer.repository.JdbiRepository

@JdbiRepository
interface OrderItemDao {
	@SqlQuery("""
		SELECT * FROM order_items
		WHERE order_id = :orderId
	""")
	fun findByOrderId(orderId: OrderId): List<OrderItemEntity>

	@SqlUpdate("""
		INSERT INTO order_items (
		id,
		order_id,
		quantity
		) VALUES (
		:id,
		:orderId,
		:quantity
		)
	""")
	fun save(items: List<OrderItemEntity>)
}
