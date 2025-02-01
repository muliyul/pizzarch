package com.muliyul.pizzarch.domain.usecase

import com.muliyul.pizzarch.domain.annotations.*

@DomainService
fun interface UseCase<IN, OUT> {
	fun execute(input: IN): OUT
}
