/**
 * 
 */
package com.prodyna.pac.ressys.util.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Special mapper for thrown exception in the application.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<Exception> {

	@Inject
	private Logger log;

	/**
	 * 
	 */
	public RestExceptionMapper() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(Exception e) {
		log.info("START toResponse() ...");
		Response.ResponseBuilder builder;
		// Handle generic exceptions
		Map<String, String> responseObj = new HashMap<String, String>();
		responseObj.put("error", e.getMessage());
		builder = Response.status(Response.Status.BAD_REQUEST).entity(e);
		log.info("END toResponse().");
		return builder.build();
	}

}
