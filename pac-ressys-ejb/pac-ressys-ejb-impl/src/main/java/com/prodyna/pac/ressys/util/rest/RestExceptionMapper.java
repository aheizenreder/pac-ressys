/**
 * 
 */
package com.prodyna.pac.ressys.util.rest;

import java.util.HashMap;
import java.util.Map;

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

	/**
	 * 
	 */
	public RestExceptionMapper() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(Exception e) {
		Response.ResponseBuilder builder;
		// Handle generic exceptions
		Map<String, String> responseObj = new HashMap<String, String>();
		responseObj.put("error", e.getMessage());
		builder = Response.status(Response.Status.BAD_REQUEST).entity(e);
		return builder.build();
	}

}
