package org.springframework.cloud.contract.spec.internal

import java.nio.charset.Charset
import java.util.regex.Pattern

val common = Common()

abstract class ValueSpec<out T : DslProperty<Any>> internal constructor(private val delegate: RegexCreatingProperty<T>) {
	operator fun invoke(any: Any) = DslProperty(any)
	fun server(serverValue: Any) = ServerValueSpec(serverValue)
	fun producer(serverValue: Any) = ServerValueSpec(serverValue)
	fun test(serverValue: Any) = ServerValueSpec(serverValue)
	fun p(serverValue: Any) = ServerValueSpec(serverValue)

	fun client(clientValue: Any) = ClientValueSpec(clientValue)
	fun consumer(clientValue: Any) = ClientValueSpec(clientValue)
	fun stub(clientValue: Any) = ClientValueSpec(clientValue)
	fun c(clientValue: Any) = ClientValueSpec(clientValue)

	val anyAlphaUnicode: T
		get() = delegate.anyAlphaUnicode()

	val anyAlphaNumeric: T
		get() = delegate.anyAlphaNumeric()

	val anyNumber: T
		get() = delegate.anyNumber()

	val anyInteger: T
		get() = delegate.anyInteger()

	val anyPositiveInt: T
		get() = delegate.anyPositiveInt()

	val anyDouble: T
		get() = delegate.anyDouble()

	val anyHex: T
		get() = delegate.anyHex()

	val aBoolean: T
		get() = delegate.aBoolean()

	val anyIpAddress: T
		get() = delegate.anyIpAddress()

	val anyHostname: T
		get() = delegate.anyHostname()

	val anyEmail: T
		get() = delegate.anyEmail()

	val anyUrl: T
		get() = delegate.anyUrl()

	val anyHttpsUrl: T
		get() = delegate.anyHttpsUrl()

	val anyUuid: T
		get() = delegate.anyUuid()

	val anyDate: T
		get() = delegate.anyDate()

	val anyDateTime: T
		get() = delegate.anyDateTime()

	val anyTime: T
		get() = delegate.anyTime()

	val anyIso8601WithOffset: T
		get() = delegate.anyIso8601WithOffset()

	val anyNonBlankString: T
		get() = delegate.anyNonBlankString()

	val anyNonEmptyString: T
		get() = delegate.anyNonEmptyString()

	fun regex(regex: String) = RegexProperty(Pattern.compile(regex))

	fun anyOf(vararg values: String?): T = delegate.anyOf(*values)

	fun file(relativePath: String, charset: Charset = Charset.defaultCharset()) = common.file(relativePath, charset)

	fun fileAsBytes(relativePath: String) = common.fileAsBytes(relativePath)

}

class ServerValueSpec(private val serverValue: Any) {
	fun client(clientValue: Any) = DslProperty(clientValue, serverValue)
	fun consumer(clientValue: Any) = DslProperty(clientValue, serverValue)
	fun stub(clientValue: Any) = DslProperty(clientValue, serverValue)
	fun c(clientValue: Any) = DslProperty(clientValue, serverValue)
}

class ClientValueSpec(private val clientValue: Any) {
	fun server(serverValue: Any) = DslProperty(clientValue, serverValue)
	fun producer(serverValue: Any) = DslProperty(clientValue, serverValue)
	fun test(serverValue: Any) = DslProperty(clientValue, serverValue)
	fun p(serverValue: Any) = DslProperty(clientValue, serverValue)
}
