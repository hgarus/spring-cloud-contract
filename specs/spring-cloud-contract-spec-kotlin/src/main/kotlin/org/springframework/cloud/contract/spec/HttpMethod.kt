package org.springframework.cloud.contract.spec

import org.springframework.cloud.contract.spec.internal.HttpMethods

enum class HttpMethod(internal val httpMethod: HttpMethods.HttpMethod) {
	GET(HttpMethods.HttpMethod.GET),
	POST(HttpMethods.HttpMethod.POST),
	PUT(HttpMethods.HttpMethod.PUT),
	PATCH(HttpMethods.HttpMethod.PATCH),
	DELETE(HttpMethods.HttpMethod.DELETE),
	OPTIONS(HttpMethods.HttpMethod.OPTIONS),
	TRACE(HttpMethods.HttpMethod.TRACE);


	companion object {
		private val nameToMethod = values()
				.map { it.httpMethod.name to it }
				.toMap()

		fun fromName(name: String) = nameToMethod[name]
	}
}