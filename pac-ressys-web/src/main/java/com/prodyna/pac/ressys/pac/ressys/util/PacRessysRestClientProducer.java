/**
 * 
 */
package com.prodyna.pac.ressys.pac.ressys.util;

import com.prodyna.pac.ressys.util.rest.RestClientProducer;

/**
 * Specialized Rest Client producer.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class PacRessysRestClientProducer {

	public static final String PAC_RESSYS_REST_URL = "http://localhost:8080/pac-ressys-web/rest/";

	public static <C> C getPacRessysService(Class<C> serviceClass) {
		return RestClientProducer.createServiceClient(PAC_RESSYS_REST_URL,
				serviceClass);
	}

	public static <C> C getPacRessysService(String authorizationHeader,
			Class<C> serviceClass) {
		return RestClientProducer.createServiceClient(PAC_RESSYS_REST_URL,
				authorizationHeader, serviceClass);
	}

	public static <C> C getPacRessysService(String loginName, String password,
			Class<C> serviceClass) {

		return RestClientProducer.createServiceClient(PAC_RESSYS_REST_URL,
				loginName, password, serviceClass);
	}

}
