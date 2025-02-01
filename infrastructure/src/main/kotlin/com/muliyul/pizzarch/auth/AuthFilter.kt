package com.muliyul.pizzarch.auth

import com.github.benmanes.caffeine.cache.*
import com.muliyul.pizzarch.*
import com.muliyul.pizzarch.config.*
import com.muliyul.pizzarch.domain.support.*
import io.dropwizard.auth.*
import io.dropwizard.auth.basic.*
import io.dropwizard.auth.chained.*
import io.dropwizard.core.setup.*
import jakarta.inject.*
import jakarta.ws.rs.container.*
import jakarta.ws.rs.ext.Provider
import org.glassfish.jersey.server.filter.*
import ru.vyarus.dropwizard.guice.module.yaml.bind.Config
import java.util.*


@Provider
class AuthFilter @Inject constructor(
	@Config configuration: AuthConfiguration,
	environment: Environment,
	basicAuthenticator: BasicUserAuthenticator,
	userAuthorizer: UserAuthorizer
) : AuthDynamicFeature(
	ChainedAuthFilter<Any, User>(
		listOf(
			BasicCredentialAuthFilter.Builder<User>()
				.setAuthenticator(
					CachingAuthenticator(
						environment.metrics(),
						basicAuthenticator,
						Caffeine.from(configuration.authenticationCacheSpec)
					)
				)
				.setAuthorizer(
					CachingAuthorizer(
						environment.metrics(),
						userAuthorizer,
						Caffeine.from(configuration.authorizationCacheSpec)
					)
				)
				.buildAuthFilter()
		)
	)
) {
	init {
		environment.jersey().register(RolesAllowedDynamicFeature::class.java)
		environment.jersey().register(AuthValueFactoryProvider.Binder(User::class.java))
	}
}

@Singleton
class BasicUserAuthenticator @Inject constructor(
//	private val userRepository: UserRepository
) : Authenticator<BasicCredentials, User> {
	override fun authenticate(credentials: BasicCredentials): Optional<User> {
//		val user = userRepository.findByEmail(credentials.username)
//
//		return Optional.ofNullable(user)
		return Optional.ofNullable(User(UserId(), Email("m@m.com")))
	}
}

@Singleton
class UserAuthorizer @Inject constructor(

) : Authorizer<User> {
	override fun authorize(user: User, role: String, p2: ContainerRequestContext?): Boolean = true
}
