package com.muliyul.pizzarch

import org.javamoney.moneta.*
import javax.money.*

fun <T> Iterable<T>.sumOf(selector: (T) -> MonetaryAmount): MonetaryAmount =
	fold(Money.of(0, "USD")) { acc, element -> acc.add(selector(element)) }
