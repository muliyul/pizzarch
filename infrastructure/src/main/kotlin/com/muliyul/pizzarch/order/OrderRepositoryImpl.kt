package com.muliyul.pizzarch.order

import com.muliyul.pizzarch.*
import com.muliyul.pizzarch.address.*
import com.muliyul.pizzarch.domain.address.*
import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.item.*
import com.muliyul.pizzarch.domain.item.LineItem
import com.muliyul.pizzarch.domain.order.*
import jakarta.inject.*
import kotlinx.coroutines.*
import nl.hiddewieringa.money.*
import ru.vyarus.guicey.jdbi3.tx.*
import java.time.*

class OrderRepositoryImpl @Inject constructor(
	private val orderDao: OrderDao,
	private val customerRepository: CustomerRepository,
	private val itemRepository: ItemRepository,
	private val addressRepository: AddressRepository,
	private val orderItemDao: OrderItemDao,
	private val timezoneResolver: TimezoneResolver
) : OrderRepository {

	override suspend fun findById(orderId: OrderId): Order? = withContext(Dispatchers.IO) {
		val order = async { orderDao.findByIdOrNull(orderId) }
		val items = async {
			val itemEntities = orderItemDao.findByOrderId(orderId)

			itemRepository.findByIds(itemEntities.map { ItemId(it.itemId) }).associateWith { item ->
				itemEntities.first { ItemId(it.itemId) == item.id }.quantity
			}
		}

		order.await()?.let {
			val customer = async { customerRepository.findById(it.customerId) }
			val address = async { addressRepository.findById(it.addressId) }

			val timezone = timezoneResolver.getTimezone(address.await())

			Order(
				id = OrderId(it.externalId),
				customer = customer.await(),
				items = items.await().map { (item, quantity) -> LineItem(item, quantity) },
				status = it.status,
				address = address.await(),
				scheduledFor = ZonedDateTime.ofInstant(it.scheduledFor, timezone),
				orderedAt = ZonedDateTime.ofInstant(it.orderedAt, timezone),
			)
		}
	}

	@InTransaction
	override suspend fun save(order: Order): Order {
		val orderEntity = order.toEntity()
		orderDao.save(orderEntity)
		val items = order.items.map { it.toEntity(order.id) }
		orderItemDao.save(items)
		return findById(order.id) ?: error("Order not found after saving")
	}

}

fun Order.toEntity() = OrderEntity(
	externalId = id.value,
	customerId = customer.id,
	total = items.sumOf { it.quantity * it.item.price },
	orderedAt = orderedAt.toInstant(),
	scheduledFor = scheduledFor.toInstant(),
	receivedAt = receivedAt?.toInstant(),
	paymentId = payment.id,
	status = status,
	addressId = address.id
)

fun LineItem.toEntity(orderId: OrderId) = OrderItemEntity(
	itemId = item.id.value,
	orderId = orderId.value,
	quantity = quantity
)
