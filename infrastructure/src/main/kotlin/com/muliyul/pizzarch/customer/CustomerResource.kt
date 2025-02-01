package com.muliyul.pizzarch.customer

import com.muliyul.pizzarch.domain.customer.*
import jakarta.inject.*
import jakarta.ws.rs.*
import jakarta.ws.rs.core.*
import kotlinx.coroutines.*
import ru.vyarus.guicey.admin.rest.*
import java.util.*

@AdminResource
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
class CustomerResource @Inject constructor(
	private val customerRepository: CustomerRepository
) {
	@GET
	@Path("/{id}")
	fun getCustomer(@PathParam("id") id: UUID): CustomerDto = runBlocking {
		customerRepository.findById(CustomerId(id)).toDto()
	}
}

fun Customer.toDto() = CustomerDto(
	id = id,
	email = email
)
