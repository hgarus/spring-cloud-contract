/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.contract.spec.internal

import org.springframework.cloud.contract.spec.HttpStatus
import org.springframework.cloud.contract.spec.toDslProperties
import org.springframework.cloud.contract.spec.toDslProperty
import org.springframework.cloud.contract.spec.util.RegexpUtils
import java.util.regex.Pattern

/**
 * Represents the response side of the HTTP communication.
 *
 * @author Tim Ysewyn
 * @since 2.2.0
 */
@ContractDslMarker
class ResponseDsl : CommonDsl() {

    private val response = Response()

    /**
     * HTTP response status.
     */
	fun status(status: Int) {
		response.status = status.toDslProperty()
	}

	/**
	 * HTTP response status.
	 */
	fun status(status: HttpStatus) {
		status(status.code)
	}

    /**
     * The HTTP response delay in milliseconds.
     */
    var delay: DslProperty<Any>? = null

    /**
     * The HTTP response headers.
     */
    private var headers: Headers? = null

    /**
     * The HTTP response cookies.
     */
    private var cookies: Cookies? = null

    /**
     * The HTTP response body.
     */
    private var body: Body? = null

    /**
     * Indicates asynchronous communication.
     */
    var async: Boolean = false

    /**
     * The HTTP response body matchers.
     */
    var bodyMatchers: ResponseBodyMatchers? = null

    fun fixedMilliseconds(delay: Long): DslProperty<Any> = delay.toDslProperty()

    fun headers(headers: HeadersDsl.() -> Unit) {
        this.headers = ResponseHeadersDsl().apply(headers).get()
    }

    fun cookies(cookies: CookiesDsl.() -> Unit) {
        this.cookies = ResponseCookiesDsl().apply(cookies).get()
    }

    fun body(body: Map<String, Any>) = Body(body.toDslProperties())

    fun body(vararg body: Pair<String, Any>) = Body(body.toMap().toDslProperties())

    fun body(body: Pair<String, Any>) = Body(mapOf(body).toDslProperties())

    fun body(body: List<Any>) = Body(body.toDslProperties())

    fun body(body: Any) = Body(body)

    fun bodyMatchers(configurer: ResponseBodyMatchersDsl.() -> Unit) {
        bodyMatchers = ResponseBodyMatchersDsl().apply(configurer).get()
    }

    /* HELPER VARIABLES */

    /* REGEX */

    val anyAlphaUnicode
        get() = response.anyAlphaUnicode()

    val anyAlphaNumeric
        get() = response.anyAlphaNumeric()

    val anyNumber
        get() = response.anyNumber()

    val anyInteger
        get() = response.anyInteger()

    val anyPositiveInt
        get() = response.anyPositiveInt()

    val anyDouble
        get() = response.anyDouble()

    val anyHex
        get() = response.anyHex()

    val aBoolean
        get() = response.aBoolean()

    val anyIpAddress
        get() = response.anyIpAddress()

    val anyHostname
        get() = response.anyHostname()

    val anyEmail
        get() = response.anyEmail()

    val anyUrl
        get() = response.anyUrl()

    val anyHttpsUrl
        get() = response.anyHttpsUrl()

    val anyUuid
        get() = response.anyUuid()

    val anyDate
        get() = response.anyDate()

    val anyDateTime
        get() = response.anyDateTime()

    val anyTime
        get() = response.anyTime()

    val anyIso8601WithOffset
        get() = response.anyIso8601WithOffset()

    val anyNonBlankString
        get() = response.anyNonBlankString()

    val anyNonEmptyString
        get() = response.anyNonEmptyString()

    /* HELPER FUNCTIONS */

    fun value(value: ClientDslProperty) = response.value(value)

    fun v(value: ClientDslProperty) = response.value(value)

    fun value(value: DslProperty<Any>) = response.value(value)

    fun v(value: DslProperty<Any>) = response.value(value)

    fun value(value: Pattern) = response.value(value)

    fun v(value: Pattern) = response.value(value)

    fun value(value: RegexProperty) = response.value(value)

    fun v(value: RegexProperty) = response.value(value)

    fun value(value: Any?) = response.value(value)

    fun v(value: Any?) = response.value(value)

    fun value(client: ClientDslProperty, server: ServerDslProperty) = response.value(client, server)

    fun v(client: ClientDslProperty, server: ServerDslProperty) = response.value(client, server)

    fun value(server: ServerDslProperty, client: ClientDslProperty) = response.value(client, server)

    fun v(server: ServerDslProperty, client: ClientDslProperty) = response.value(client, server)

    fun fromRequest() = FromRequestDsl()

    fun anyOf(vararg values: String?) = response.anyOf(*values)

    internal fun get(): Response {
		delay?.also { response.delay = delay }
		headers?.also { response.headers = headers }
        cookies?.also { response.cookies = cookies }
        body?.also { response.body = body }
        response.async = async
        bodyMatchers?.also { response.bodyMatchers = bodyMatchers }
        return response
    }

    private class ResponseHeadersDsl : HeadersDsl() {

        private val common = Common()

        override fun matching(value: Any?): Any? {
            return value?.also {
                return when (value) {
                    is String -> return this.common.value(
                            this.common.c(value),
                            this.common.p(NotToEscapePattern(Pattern.compile(RegexpUtils.escapeSpecialRegexWithSingleEscape(value) + ".*")))
                    )
                    else -> value
                }
            }
        }

    }

    private class ResponseCookiesDsl : CookiesDsl() {

        private val common = Common()

        override fun matching(value: Any?): Any? {
            return value?.also {
                return when (value) {
                    is String -> return this.common.value(
                            this.common.c(value),
                            this.common.p(this.common.regex(RegexpUtils.escapeSpecialRegexWithSingleEscape(value) + ".*"))
                    )
                    else -> value
                }
            }
        }

    }
}
