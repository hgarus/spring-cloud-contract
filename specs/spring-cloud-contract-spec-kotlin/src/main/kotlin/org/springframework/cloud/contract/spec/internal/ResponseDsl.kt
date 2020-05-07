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
import java.time.Duration
import java.util.regex.Pattern

/**
 * Represents the response side of the HTTP communication.
 *
 * @author Tim Ysewyn
 * @since 2.2.0
 */
@ContractDslMarker
class ResponseDsl {

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
     * HTTP response delay
     */
	val delay = DelaySpec()

	inner class DelaySpec {
		fun fixed(duration: Duration) {
			response.delay = duration.toMillis().toDslProperty()
		}
	}

    /**
     * Indicates asynchronous communication.
     */
    var async: Boolean
		get() = response.async
		set(value) { response.async = value}


	/**
	 * HTTP headers sent with the response.
	 */
    fun headers(headers: HeadersDsl.() -> Unit) {
        response.headers = ResponseHeadersDsl().apply(headers).get()
    }

	/**
	 * HTTP cookies sent with the response.
	 */
    fun cookies(cookies: CookiesDsl.() -> Unit) {
        response.cookies = ResponseCookiesDsl().apply(cookies).get()
    }
	/**
	 * Body of the HTTP response
	 */
    fun body(body: Map<String, Any>) {
		response.body = Body(body.toDslProperties())
	}
	/**
	 * Body of the HTTP response
	 */
    fun body(vararg body: Pair<String, Any>) {
		response.body = Body(body.toMap().toDslProperties())
	}
	/**
	 * Body of the HTTP response
	 */
    fun body(body: Pair<String, Any>) {
		response.body = Body(mapOf(body).toDslProperties())
	}
	/**
	 * Body of the HTTP response
	 */
    fun body(body: List<Any>) {
		response.body = Body(body.toDslProperties())
	}
	/**
	 * Body of the HTTP response
	 */
    fun body(body: Any) {
		response.body = Body(body)
	}

	/**
	 * The HTTP response body matchers.
	 */
    fun bodyMatchers(configurer: ResponseBodyMatchersDsl.() -> Unit) {
        response.bodyMatchers = ResponseBodyMatchersDsl().apply(configurer).get()
    }

	/**
	 * Constants and functions to describe values using regular expressions
	 */
	val regex = RegexSpec()
	/**
	 * Constants and functions to describe values using regular expressions
	 */
	val r = regex


	class ResponseValueSpec : ValueSpec<ServerDslProperty>(Response()) {
		fun fromRequest() = FromRequestDsl()
	}

	/**
	 * Constants and functions to describe values
	 */
	val value = ResponseValueSpec()
	/**
	 * Constants and functions to describe values
	 */
	val v = value

    internal fun get(): Response = response

    private inner class ResponseHeadersDsl : HeadersDsl() {

        override fun matching(value: Any?): Any? {
            return value?.also {
                return when (value) {
                    is String -> v
							.consumer(value)
							.producer(NotToEscapePattern(Pattern.compile(RegexpUtils.escapeSpecialRegexWithSingleEscape(value) + ".*")))
                    else -> value
                }
            }
        }

    }

    private inner class ResponseCookiesDsl : CookiesDsl() {

        override fun matching(value: Any?): Any? {
            return value?.also {
                return when (value) {
                    is String -> v
							.consumer(value)
							.producer(regex(RegexpUtils.escapeSpecialRegexWithSingleEscape(value) + ".*"))
                    else -> value
                }
            }
        }

    }
}
