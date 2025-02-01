package com.muliyul.pizzarch.domain.ddd

import com.muliyul.pizzarch.domain.annotations.*

@DomainService
interface Repository<ID, MODEL : Identifiable<ID>> {
	suspend fun findByIdOrNull(id: ID): MODEL?
	suspend fun save(model: MODEL): MODEL
}
