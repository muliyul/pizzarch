package com.muliyul.pizzarch.customer

import com.muliyul.pizzarch.domain.customer.*
import com.muliyul.pizzarch.domain.support.*
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import ru.vyarus.guicey.jdbi3.installer.repository.*
import ru.vyarus.guicey.jdbi3.tx.*

@JdbiRepository
@InTransaction
interface CustomerDao {
	@SqlQuery("SELECT * FROM customers WHERE external_id = :customerId")
	fun findByIdOrNull(customerId: CustomerId): CustomerEntity?

	@SqlQuery("SELECT * FROM customers WHERE email = :email")
	fun findByEmailOrNull(email: Email): CustomerEntity?

	@SqlUpdate("INSERT INTO customers (external_id, email) VALUES (:externalId, :email) ON duplicate key update email = :email")
	fun save(entity: CustomerEntity)
}
