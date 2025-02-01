package com.muliyul.pizzarch.order

import com.google.common.eventbus.*
import com.muliyul.pizzarch.address.*
import com.muliyul.pizzarch.domain.address.*
import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.item.*
import com.muliyul.pizzarch.domain.order.*
import com.muliyul.pizzarch.domain.payment.*
import com.muliyul.pizzarch.domain.usecase.order.*
import com.muliyul.pizzarch.order.events.*
import com.muliyul.pizzarch.payment.event.*
import jakarta.inject.*
import kotlinx.coroutines.*
import java.time.*

class CreateOrderUseCaseImpl @Inject constructor(
	private val orderRepository: OrderRepository,
	private val customerRepository: CustomerRepository,
	private val itemRepository: ItemRepository,
	private val addressRepository: AddressRepository,
	private val timezoneResolver: TimezoneResolver,
	private val paymentRepository: PaymentRepository,
	private val eventBus: EventBus
) : CreateOrderUseCase {
	override fun execute(input: CreateOrderInput): Order = runBlocking {
		val (request, customerId) = input

		val addressTimeZone = async {
			val address = addressRepository.findById(request.addressId)
			timezoneResolver.getTimezone(address)
		}
		val items = async { itemRepository.findByIds(request.items.map { it.item.id }) }

		val timeZone = addressTimeZone.await()
		val order = Order(
			customerId = customerId,
			items = items.await().map { item ->
				val itemDto = input.request.items.first { it.item.id == item.id }
				LineItem(item, itemDto.quantity)
			},
			addressId = input.request.addressId,
			scheduledFor = request.scheduledFor.atZone(timeZone),
			orderedAt = ZonedDateTime.now(timeZone),
			paymentId = input.paymentId
		)

		orderRepository.save(order).also {
			eventBus.post(OrderCreatedEvent(it))
		}
	}

	@Subscribe
	fun onPaymentReceived(event: PaymentReceivedEvent) = runBlocking(Dispatchers.IO) {
		val order = async { orderRepository.findById(event.orderId) ?: error("Order not found") }
		val payment = async { paymentRepository.findById(event.paymentId) ?: error("Payment not found") }

		orderRepository.save(order.await().apply {
			this.payment = payment.await()
		})
	}
}

