package com.muliyul.pizzarch.address

import com.muliyul.pizzarch.domain.address.*
import ru.vyarus.guicey.jdbi3.installer.repository.*

@JdbiRepository
interface AddressDao {
	fun findByIdOrNull(addressId: AddressId): AddressEntity?
	fun save(entity: AddressEntity)
}
