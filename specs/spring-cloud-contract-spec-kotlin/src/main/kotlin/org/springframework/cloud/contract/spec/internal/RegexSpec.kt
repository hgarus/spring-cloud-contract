package org.springframework.cloud.contract.spec.internal

import java.util.regex.Pattern

class RegexSpec {
	operator fun invoke(regex: String) = RegexProperty(Pattern.compile(regex))

	val onlyAlphaUnicode: RegexProperty
		get() = RegexPatterns.onlyAlphaUnicode()

	val alphaNumeric: RegexProperty
		get() = RegexPatterns.alphaNumeric()

	val number: RegexProperty
		get() = RegexPatterns.number()

	val positiveInt: RegexProperty
		get() = RegexPatterns.positiveInt()

	val anyBoolean: RegexProperty
		get() = RegexPatterns.anyBoolean()

	val anInteger: RegexProperty
		get() = RegexPatterns.anInteger()

	val aDouble: RegexProperty
		get() = RegexPatterns.aDouble()

	val ipAddress: RegexProperty
		get() = RegexPatterns.ipAddress()

	val hostname: RegexProperty
		get() = RegexPatterns.hostname()

	val email: RegexProperty
		get() = RegexPatterns.email()

	val anUrl: RegexProperty
		get() = RegexPatterns.url()

	val anHttpsUrl: RegexProperty
		get() = RegexPatterns.httpsUrl()

	val uuid: RegexProperty
		get() = RegexPatterns.uuid()

	val isoDate: RegexProperty
		get() = RegexPatterns.isoDate()

	val isoDateTime: RegexProperty
		get() = RegexPatterns.isoDateTime()

	val isoTime: RegexProperty
		get() = RegexPatterns.isoTime()

	val iso8601WithOffset: RegexProperty
		get() = RegexPatterns.iso8601WithOffset()

	val nonEmpty: RegexProperty
		get() = RegexPatterns.nonEmpty()

	val nonBlank: RegexProperty
		get() = RegexPatterns.nonBlank()

}