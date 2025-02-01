package com.muliyul.pizzarch.item

import com.muliyul.pizzarch.domain.item.*

class ItemRepositoryImpl : ItemRepository {
	override suspend fun findByIdOrNull(id: ItemId): Item? {
		TODO("Not yet implemented")
	}

	override suspend fun save(model: Item): Item {
		TODO("Not yet implemented")
	}

	override suspend fun findByIds(itemIds: List<ItemId>): List<Item> {
		TODO("Not yet implemented")
	}

}
