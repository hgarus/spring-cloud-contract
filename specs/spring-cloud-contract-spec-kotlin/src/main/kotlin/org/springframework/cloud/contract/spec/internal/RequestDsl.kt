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
import org.springframework.cloud.contract.spec.toDslProperty
import org.springframework.cloud.contract.spec.util.RegexpUtils

/**
 * Represents the request side of the HTTP communication.
 *
 * @author Tim Ysewyn
 * @since 2.2.0
 */
@ContractDslMarker
class RequestDsl  {

    /**
     * The HTTP method.
     */
    var method: HttpMethod? = null

    /**
     * The URL to which the request will be sent.
     */
    private var url: Url? = null

    /**
     * The URL to which the request will be sent.
     */
    private var urlPath: UrlPath? = null

    /**
     * The HTTP headers which should be sent with the request.
     */
    private var headers: Headers? = null

    /**
     * The HTTP cookies which should be sent with the request.
     */
    private var cookies: Cookies? = null

    /**
     * The HTTP request body which should be sent.
     */
    private var body: Body? = null

    /**
     * The content that needs to be sent with a multipart HTTP request.
     */
    private var multipart: Multipart? = null

    /**
     * The HTTP request body matchers.
     */
    private var bodyMatchers: BodyMatchers? = null

    fun url(url: String) {
		this.url = Url(url)
	}

    fun url(url: DslProperty<Any>) {
		this.url = Url(url)
	}

    fun url(client: ClientDslProperty, server: ServerDslProperty) {
		this.url = Url(value.client(client).server(server))
	}

    fun urlPath(path: String) {
		urlPath = UrlPath(path)
	}

	fun urlPath(path: String, parameters: QueryParameters.() -> Unit) {
		val urlPath = UrlPath(path)
		urlPath.queryParameters = QueryParameters().apply(parameters)
		this.urlPath = urlPath
	}

    fun headers(headers: HeadersDsl.() -> Unit) {
        this.headers = RequestHeadersDsl().apply(headers).get()
    }

    fun cookies(cookies: CookiesDsl.() -> Unit) {
        this.cookies = RequestCookiesDsl().apply(cookies).get()
    }

    fun body(body: Map<String, Any>) {
		this.body = Body(body.toDslProperties())
	}

    fun body(vararg body: Pair<String, Any>) {
		this.body = Body(body.toMap().toDslProperties())
	}

    fun body(body: Pair<String, Any>) {
		this.body = Body(mapOf(body).toDslProperties())
	}

    fun body(body: List<Any>) {
		this.body = Body(body.toDslProperties())
	}

    fun body(body: Any) {
		this.body = Body(body)
	}

    fun multipart(configurer: MultipartDsl.() -> Unit) {
        this.multipart = MultipartDsl().apply(configurer).get()
    }

    fun bodyMatchers(configurer: BodyMatchersDsl.() -> Unit) {
        this.bodyMatchers = BodyMatchersDsl().apply(configurer).get()
    }

	val r = RegexSpec()
	val regex = RegexSpec()

	class RequestValueSpec : ValueSpec<ClientDslProperty>(Request())
	val v
		get() = RequestValueSpec()
	val value
		get() = RequestValueSpec()

    /* HELPER FUNCTIONS */

    /**
     * Sets the equality check to the given query parameter.
     * @param value value to check against
     * @return matching strategy
     */
    fun equalTo(value: Any) = MatchingStrategy(value, MatchingStrategy.Type.EQUAL_TO)

    /**
     * Sets the containing check to the given query parameter.
     * @param value value to check against
     * @return matching strategy
     */
    fun containing(value: Any) = MatchingStrategy(value, MatchingStrategy.Type.CONTAINS)

    /**
     * Sets the matching check to the given query parameter.
     * @param value value to check against
     * @return matching strategy
     */
    fun matching(value: Any) = MatchingStrategy(value, MatchingStrategy.Type.MATCHING)

    /**
     * Sets the not matching check to the given query parameter.
     * @param value value to check against
     * @return matching strategy
     */
    fun notMatching(value: Any) = MatchingStrategy(value, MatchingStrategy.Type.NOT_MATCHING)

    /**
     * Sets the XML equality check to the body.
     * @param value value to check against
     * @return matching strategy
     */
    fun equalToXml(value: Any) = MatchingStrategy(value, MatchingStrategy.Type.EQUAL_TO_XML)

    /**
     * Sets the JSON equality check to the body.
     * @param value value to check against
     * @return matching strategy
     */
    fun equalToJson(value: Any) = MatchingStrategy(value, MatchingStrategy.Type.EQUAL_TO_JSON)

    /**
     * Sets absence check to the given query parameter.
     * @return matching strategy
     */
    fun absent() = MatchingStrategy(true, MatchingStrategy.Type.ABSENT)


    internal fun get(): Request {
        val request = Request()
        request.method = method?.httpMethod?.name?.toDslProperty()
        url?.also { request.url = url }
        urlPath?.also { request.urlPath = urlPath }
        headers?.also { request.headers = headers }
        cookies?.also { request.cookies = cookies }
        body?.also { request.body = body }
        multipart?.also { request.multipart = multipart }
        bodyMatchers?.also { request.bodyMatchers = bodyMatchers }
        return request
    }

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