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

import org.springframework.cloud.contract.spec.HttpMethod
import org.springframework.cloud.contract.spec.toDslProperties
import org.springframework.cloud.contract.spec.util.RegexpUtils

/**
 * Represents the request side of the HTTP communication.
 *
 * @author Tim Ysewyn
 * @since 2.2.0
 */
@ContractDslMarker
class RequestDsl  {

	private val request = Request()

	/**
     * HTTP method of the request
     */
    var method: HttpMethod? = null

	/**
	 * URL where the request will be sent.
	 */
    fun url(url: String) {
		request.url = Url(url)
	}

	/**
	 * URL where the request will be sent.
	 */
    fun url(url: DslProperty<Any>) {
		request.url = Url(url)
	}

	/**
	 * URL where the request will be sent.
	 */
    fun url(client: ClientDslProperty, server: ServerDslProperty) {
		request.url = Url(value.client(client).server(server))
	}

    fun urlPath(path: String) {
		request.urlPath = UrlPath(path)
	}

	/**
	 * URL where the request will be sent.
	 */
	fun urlPath(path: String, parameters: QueryParameters.() -> Unit) {
		val urlPath = UrlPath(path)
		urlPath.queryParameters = QueryParameters().apply(parameters)
		request.urlPath = urlPath
	}

	/**
	 * HTTP headers sent with the request.
	 */
    fun headers(headers: HeadersDsl.() -> Unit) {
        request.headers = RequestHeadersDsl().apply(headers).get()
    }

	/**
	 * HTTP cookies sent with the request.
	 */
    fun cookies(cookies: CookiesDsl.() -> Unit) {
        request.cookies = RequestCookiesDsl().apply(cookies).get()
    }

	/**
	 * Body of the HTTP request
	 */
    fun body(body: Map<String, Any>) {
		request.body = Body(body.toDslProperties())
	}

	/**
	 * Body of the HTTP request
	 */
    fun body(vararg body: Pair<String, Any>) {
		request.body = Body(body.toMap().toDslProperties())
	}

	/**
	 * Body of the HTTP request
	 */
    fun body(body: Pair<String, Any>) {
		request.body = Body(mapOf(body).toDslProperties())
	}

	/**
	 * Body of the HTTP request
	 */
    fun body(body: List<Any>) {
		request.body = Body(body.toDslProperties())
	}

	/**
	 * Body of the HTTP request
	 */
    fun body(body: Any) {
		request.body = Body(body)
	}

	/**
	 * Content of a multipart HTTP request
	 */
    fun multipart(configurer: MultipartDsl.() -> Unit) {
        request.multipart = MultipartDsl().apply(configurer).get()
    }

	/**
	 * BodyMatchers for the HTTP request
	 */
    fun bodyMatchers(configurer: BodyMatchersDsl.() -> Unit) {
        request.bodyMatchers = BodyMatchersDsl().apply(configurer).get()
    }

	/**
	 * Constants and functions to describe values using regular expressions
	 */
	val r = RegexSpec()
	/**
	 * Constants and functions to describe values using regular expressions
	 */
	val regex = RegexSpec()

	class RequestValueSpec : ValueSpec<ClientDslProperty>(Request())
	/**
	 * Constants and functions to describe values
	 */
	val v
		get() = RequestValueSpec()
	/**
	 * Constants and functions to describe values
	 */
	val value
		get() = RequestValueSpec()

    internal fun get() = request

    private inner class RequestHeadersDsl : HeadersDsl() {

        override fun matching(value: Any?): Any? {
            return value?.also {
                return when (value) {
                    is String -> v
							.consumer(regex(RegexpUtils.escapeSpecialRegexWithSingleEscape(value) + ".*"))
							.producer(value)
                    else -> value
                }
            }
        }

    }

    private inner class RequestCookiesDsl : CookiesDsl() {

        override fun matching(value: Any?): Any? {
            return value?.also {
                return when (value) {
                    is String -> v
							.consumer(regex(RegexpUtils.escapeSpecialRegexWithSingleEscape(value) + ".*"))
							.producer(value)
                    else -> value
                }
            }
        }

	}
}