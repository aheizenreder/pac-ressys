/**
 * 
 */
package com.prodyna.pac.ressys.util.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
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

	public static 	<C> C createServiceClient(String url, String authorizationHeader, Class<C> clazz) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		ResteasyWebTarget resteasyWebTarget = (ResteasyWebTarget) target;
		resteasyWebTarget.request().header(HttpHeaders.AUTHORIZATION, authorizationHeader);
		C service = resteasyWebTarget.proxy(clazz);

		return service;

	}

	public static 	<C> C createServiceClient(String url, String loginName, String password, Class<C> clazz) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		ResteasyWebTarget resteasyWebTarget = (ResteasyWebTarget) target;
		resteasyWebTarget.register(new BasicAuthentication(loginName, password));
		C service = resteasyWebTarget.proxy(clazz);

		return service;

	}

}
