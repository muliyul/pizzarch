package com.muliyul.pizzarch.order

import com.google.inject.AbstractModule
import com.muliyul.pizzarch.address.*
import com.muliyul.pizzarch.customer.*
import com.muliyul.pizzarch.domain.address.*
import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.item.*
import com.muliyul.pizzarch.domain.order.*
import com.muliyul.pizzarch.domain.payment.*
import com.muliyul.pizzarch.domain.usecase.order.*
import com.muliyul.pizzarch.item.*
import com.muliyul.pizzarch.payment.*
import jakarta.inject.Singleton

object OrderModule : AbstractModule() {
	override fun configure() {
		bind(AddressRepository::class.java).to(AddressRepositoryImpl::class.java).`in`(Singleton::class.java)
		bind(CustomerRepository::class.java).to(CustomerRepositoryImpl::class.java).`in`(Singleton::class.java)
		bind(ItemRepository::class.java).to(ItemRepositoryImpl::class.java).`in`(Singleton::class.java)
		bind(OrderRepository::class.java).to(OrderRepositoryImpl::class.java).`in`(Singleton::class.java)
		bind(PaymentRepository::class.java).to(PaymentRepositoryImpl::class.java).`in`(Singleton::class.java)
		bind(TimezoneResolver::class.java).to(TimezoneResolverImpl::class.java).`in`(Singleton::class.java)

		bind(CreateOrderUseCase::class.java).to(CreateOrderUseCaseImpl::class.java).`in`(Singleton::class.java)
	}
}

