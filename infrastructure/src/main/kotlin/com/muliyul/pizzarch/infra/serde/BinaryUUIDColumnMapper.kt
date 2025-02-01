package com.muliyul.pizzarch.infra.serde

import org.jdbi.v3.core.mapper.*
import org.jdbi.v3.core.statement.*
import java.nio.*
import java.sql.*
import java.util.*

object BinaryUUIDColumnMapper : ColumnMapper<UUID?> {
	override fun map(rs: ResultSet, columnNumber: Int, ctx: StatementContext): UUID? {
		val buffer = rs.getBytes(columnNumber)?.let { ByteBuffer.wrap(it) }

		val let = buffer?.let { UUID(it.long, it.long) }
		return let
	}
}
