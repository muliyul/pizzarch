package com.muliyul.pizzarch.order.api

import com.muliyul.pizzarch.auth.*
import com.muliyul.pizzarch.domain.order.*
import io.dropwizard.auth.Auth
import jakarta.ws.rs.*
import jakarta.ws.rs.core.*


@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
interface OrderApi {
	@POST
	fun createOrder(request: CreateOrderRequest, @Auth user: User): OrderDto
}
