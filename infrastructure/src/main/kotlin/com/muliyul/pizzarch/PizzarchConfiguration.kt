package com.muliyul.pizzarch

import com.fasterxml.jackson.annotation.JsonProperty
import com.muliyul.pizzarch.config.*
import io.dropwizard.core.*
import io.dropwizard.db.*
import io.dropwizard.flyway.*

data class PizzarchConfiguration(
	@JsonProperty("flyway")
	val flywayFactory: FlywayFactory = FlywayFactory(),
	val database: DataSourceFactory = DataSourceFactory(),
	val auth: AuthConfiguration = AuthConfiguration()
) : Configuration(),
	FlywayConfiguration<PizzarchConfiguration>,
	DatabaseConfiguration<PizzarchConfiguration> {

	override fun getFlywayFactory(p0: PizzarchConfiguration) = flywayFactory
	override fun getDataSourceFactory(p0: PizzarchConfiguration?) = database
}
