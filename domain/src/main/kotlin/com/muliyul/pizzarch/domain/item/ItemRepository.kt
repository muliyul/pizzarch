package com.muliyul.pizzarch.domain.item

import com.muliyul.pizzarch.domain.ddd.*

interface ItemRepository : Repository<ItemId, Item> {
	suspend fun findByIds(itemIds: List<ItemId>): List<Item>
}
