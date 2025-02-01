package com.muliyul.pizzarch

import com.fasterxml.jackson.module.kotlin.*
import com.google.common.eventbus.*
import com.google.inject.*
import com.muliyul.pizzarch.domain.annotations.*
import com.muliyul.pizzarch.infra.serde.*
import com.muliyul.pizzarch.order.*
import io.dropwizard.configuration.*
import io.dropwizard.core.*
import io.dropwizard.core.setup.*
import jakarta.inject.Singleton
import org.jdbi.v3.jackson2.*
import org.jdbi.v3.sqlobject.kotlin.*
import org.zalando.jackson.datatype.money.*
import ru.vyarus.dropwizard.guice.*
import ru.vyarus.dropwizard.guice.module.installer.*
import ru.vyarus.dropwizard.guice.module.installer.install.binding.*
import ru.vyarus.dropwizard.guice.module.installer.util.*
import ru.vyarus.guicey.admin.*
import ru.vyarus.guicey.eventbus.*
import ru.vyarus.guicey.jdbi3.*
import java.util.concurrent.*

fun main(args: Array<String>) = PizzarchService.run(*args)

object PizzarchService : Application<PizzarchConfiguration>() {
	override fun initialize(bootstrap: Bootstrap<PizzarchConfiguration>) {
		bootstrap.objectMapper.registerKotlinModule()
			.registerModule(MoneyModule())

		bootstrap.configurationSourceProvider =
			SubstitutingSourceProvider(ResourceConfigurationSourceProvider(), EnvironmentVariableSubstitutor(false))

		bootstrap.addBundle(
			GuiceBundle.builder()
				.enableAutoConfig()
				.bundles(
					AdminRestBundle("/admin/*"),
					EventBusBundle(AsyncEventBus(Executors.newSingleThreadExecutor())),
					JdbiBundle.forDatabase<PizzarchConfiguration> { c, _ -> c.database }
						.withPlugins(Jackson2Plugin(), KotlinSqlObjectPlugin())
						.withConfig {
							it.registerArgument(BinaryUUIDArgumentFactory)
							it.registerColumnMapper(BinaryUUIDColumnMapper)

							it.config[Jackson2Config::class.java].mapper = bootstrap.objectMapper
						}
				)
				.modules(
					OrderModule
				)
				.build()
		)
	}

	override fun run(config: PizzarchConfiguration, env: Environment) {
		config.flywayFactory.build(
			config.database.url,
			config.database.user,
			config.database.password
		).migrate()
	}
}

class DomainInstaller : FeatureInstaller, BindingInstaller {
	private val reporter = Reporter(javaClass, "DomainInstaller")

	override fun matches(type: Class<*>) =
		type.annotations.any { it is DomainService } && !type.kotlin.isAbstract

	override fun report() {
	}

	override fun bind(binder: Binder, type: Class<*>, lazy: Boolean) {
		binder.bind(type).apply {
			if (!lazy) asEagerSingleton()
			else `in`(Singleton::class.java)
		}
	}
}
