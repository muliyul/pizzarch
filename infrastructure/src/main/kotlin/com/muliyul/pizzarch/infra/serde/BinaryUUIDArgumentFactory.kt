package com.muliyul.pizzarch.infra.serde

import org.jdbi.v3.core.argument.*
import org.jdbi.v3.core.config.*
import java.nio.*
import java.sql.*
import java.util.*

object BinaryUUIDArgumentFactory : AbstractArgumentFactory<UUID?>(Types.BINARY) {
	override fun build(uuid: UUID?, config: ConfigRegistry) = Argument { idx, stmt, _ ->
		uuid?.also {
			val ba = ByteBuffer.wrap(ByteArray(16)).apply {
				putLong(it.mostSignificantBits)
				putLong(it.leastSignificantBits)
			}.array()

			stmt.setBytes(idx, ba)
		} ?: stmt.setNull(idx, Types.BINARY)
	}
}
