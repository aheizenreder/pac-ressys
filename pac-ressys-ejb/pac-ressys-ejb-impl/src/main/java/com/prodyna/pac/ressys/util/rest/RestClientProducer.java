/**
 * 
 */
package com.prodyna.pac.ressys.util.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * Utility class to create rest client for a service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class RestClientProducer {

	public static 	<C> C createServiceClient(String url, Class<C> clazz) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		ResteasyWebTarget resteasyWebTarget = (ResteasyWebTarget) target;

		C service = resteasyWebTarget.proxy(clazz);

		return service;

	}

}
