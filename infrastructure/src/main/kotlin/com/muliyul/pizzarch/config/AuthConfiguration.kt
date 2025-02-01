package com.muliyul.pizzarch.config

data class AuthConfiguration(
	val authenticationCacheSpec: String = "expireAfterAccess=1d",
	val authorizationCacheSpec: String = "expireAfterAccess=10m"
)
