package org.springframework.cloud.contract.spec.internal

import org.springframework.cloud.contract.spec.toDslProperty

class StatusSpec(private val response: Response) {
	var code
		get() = response.status.clientValue as Int
		set(value) { response.status = value.toDslProperty() }

	val CONTINUE : Unit
		get() { code = HttpStatus.CONTINUE }
	val SWITCHING_PROTOCOLS : Unit
		get() { code = HttpStatus.SWITCHING_PROTOCOLS }
	val PROCESSING : Unit
		get() { code = HttpStatus.PROCESSING }
	val CHECKPOINT : Unit
		get() { code = HttpStatus.CHECKPOINT }
	val OK : Unit
		get() { code = HttpStatus.OK }
	val CREATED : Unit
		get() { code = HttpStatus.CREATED }
	val ACCEPTED : Unit
		get() { code = HttpStatus.ACCEPTED }
	val NON_AUTHORITATIVE_INFORMATION : Unit
		get() { code = HttpStatus.NON_AUTHORITATIVE_INFORMATION }
	val NO_CONTENT : Unit
		get() { code = HttpStatus.NO_CONTENT }
	val RESET_CONTENT : Unit
		get() { code = HttpStatus.RESET_CONTENT }
	val PARTIAL_CONTENT : Unit
		get() { code = HttpStatus.PARTIAL_CONTENT }
	val MULTI_STATUS : Unit
		get() { code = HttpStatus.MULTI_STATUS }
	val ALREADY_REPORTED : Unit
		get() { code = HttpStatus.ALREADY_REPORTED }
	val IM_USED : Unit
		get() { code = HttpStatus.IM_USED }
	val MULTIPLE_CHOICES : Unit
		get() { code = HttpStatus.MULTIPLE_CHOICES }
	val MOVED_PERMANENTLY : Unit
		get() { code = HttpStatus.MOVED_PERMANENTLY }
	val FOUND : Unit
		get() { code = HttpStatus.FOUND }
	val SEE_OTHER : Unit
		get() { code = HttpStatus.SEE_OTHER }
	val NOT_MODIFIED : Unit
		get() { code = HttpStatus.NOT_MODIFIED }
	val TEMPORARY_REDIRECT : Unit
		get() { code = HttpStatus.TEMPORARY_REDIRECT }
	val PERMANENT_REDIRECT : Unit
		get() { code = HttpStatus.PERMANENT_REDIRECT }
	val BAD_REQUEST : Unit
		get() { code = HttpStatus.BAD_REQUEST }
	val UNAUTHORIZED : Unit
		get() { code = HttpStatus.UNAUTHORIZED }
	val PAYMENT_REQUIRED : Unit
		get() { code = HttpStatus.PAYMENT_REQUIRED }
	val FORBIDDEN : Unit
		get() { code = HttpStatus.FORBIDDEN }
	val NOT_FOUND : Unit
		get() { code = HttpStatus.NOT_FOUND }
	val METHOD_NOT_ALLOWED : Unit
		get() { code = HttpStatus.METHOD_NOT_ALLOWED }
	val NOT_ACCEPTABLE : Unit
		get() { code = HttpStatus.NOT_ACCEPTABLE }
	val PROXY_AUTHENTICATION_REQUIRED : Unit
		get() { code = HttpStatus.PROXY_AUTHENTICATION_REQUIRED }
	val REQUEST_TIMEOUT : Unit
		get() { code = HttpStatus.REQUEST_TIMEOUT }
	val CONFLICT : Unit
		get() { code = HttpStatus.CONFLICT }
	val GONE : Unit
		get() { code = HttpStatus.GONE }
	val LENGTH_REQUIRED : Unit
		get() { code = HttpStatus.LENGTH_REQUIRED }
	val PRECONDITION_FAILED : Unit
		get() { code = HttpStatus.PRECONDITION_FAILED }
	val PAYLOAD_TOO_LARGE : Unit
		get() { code = HttpStatus.PAYLOAD_TOO_LARGE }
	val UNSUPPORTED_MEDIA_TYPE : Unit
		get() { code = HttpStatus.UNSUPPORTED_MEDIA_TYPE }
	val REQUESTED_RANGE_NOT_SATISFIABLE : Unit
		get() { code = HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE }
	val EXPECTATION_FAILED : Unit
		get() { code = HttpStatus.EXPECTATION_FAILED }
	val I_AM_A_TEAPOT : Unit
		get() { code = HttpStatus.I_AM_A_TEAPOT }
	val UNPROCESSABLE_ENTITY : Unit
		get() { code = HttpStatus.UNPROCESSABLE_ENTITY }
	val LOCKED : Unit
		get() { code = HttpStatus.LOCKED }
	val FAILED_DEPENDENCY : Unit
		get() { code = HttpStatus.FAILED_DEPENDENCY }
	val UPGRADE_REQUIRED : Unit
		get() { code = HttpStatus.UPGRADE_REQUIRED }
	val PRECONDITION_REQUIRED : Unit
		get() { code = HttpStatus.PRECONDITION_REQUIRED }
	val TOO_MANY_REQUESTS : Unit
		get() { code = HttpStatus.TOO_MANY_REQUESTS }
	val REQUEST_HEADER_FIELDS_TOO_LARGE : Unit
		get() { code = HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE }
	val UNAVAILABLE_FOR_LEGAL_REASONS : Unit
		get() { code = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS }
	val INTERNAL_SERVER_ERROR : Unit
		get() { code = HttpStatus.INTERNAL_SERVER_ERROR }
	val NOT_IMPLEMENTED : Unit
		get() { code = HttpStatus.NOT_IMPLEMENTED }
	val BAD_GATEWAY : Unit
		get() { code = HttpStatus.BAD_GATEWAY }
	val SERVICE_UNAVAILABLE : Unit
		get() { code = HttpStatus.SERVICE_UNAVAILABLE }
	val GATEWAY_TIMEOUT : Unit
		get() { code = HttpStatus.GATEWAY_TIMEOUT }
	val HTTP_VERSION_NOT_SUPPORTED : Unit
		get() { code = HttpStatus.HTTP_VERSION_NOT_SUPPORTED }
	val VARIANT_ALSO_NEGOTIATES : Unit
		get() { code = HttpStatus.VARIANT_ALSO_NEGOTIATES }
	val INSUFFICIENT_STORAGE : Unit
		get() { code = HttpStatus.INSUFFICIENT_STORAGE }
	val LOOP_DETECTED : Unit
		get() { code = HttpStatus.LOOP_DETECTED }
	val BANDWIDTH_LIMIT_EXCEEDED : Unit
		get() { code = HttpStatus.BANDWIDTH_LIMIT_EXCEEDED }
	val NOT_EXTENDED : Unit
		get() { code = HttpStatus.NOT_EXTENDED }
	val NETWORK_AUTHENTICATION_REQUIRED : Unit
		get() { code = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED }

}
