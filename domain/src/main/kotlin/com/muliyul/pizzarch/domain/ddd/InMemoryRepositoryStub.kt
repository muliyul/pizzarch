package com.muliyul.pizzarch.domain.ddd

import com.muliyul.pizzarch.domain.annotations.*
import java.util.concurrent.*

@DomainStub
class InMemoryRepositoryStub<ID, MODEL : Identifiable<ID>> : Repository<ID, MODEL> {
	private val models = ConcurrentHashMap<ID, MODEL>()

	override suspend fun findByIdOrNull(id: ID): MODEL? = models[id]

	override suspend fun save(model: MODEL): MODEL {
		models[model.id] = model
		return model
	}
}
