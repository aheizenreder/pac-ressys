/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.prodyna.pac.ressys.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {
	/**
	 * Constant for used hash algorithm.
	 */
	private static final String HASH_ALGORITHM_SHA_256 = "SHA-256";

	// use @SuppressWarnings to tell IDE to ignore warnings about field not
	// being referenced directly
	@Produces
	@PersistenceContext
	private EntityManager em;

	private InitialContext initialContext;

	public Resources() {
		try {
			initialContext = new InitialContext();
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
				.getName());
	}

	@Produces
	public InitialContext produceInitialContext() {
		return initialContext;
	}

	@Produces
	public QueueConnectionFactory produceQueueConnectionFactory() {
		try {
			return (QueueConnectionFactory) produceInitialContext().lookup(
					"ConnectionFactory");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Produces
	public MessageDigest produceMessageDigest() {
		try {
			return MessageDigest.getInstance(HASH_ALGORITHM_SHA_256);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(
					"Can not create MessageDigest instance for "+HASH_ALGORITHM_SHA_256+".", e);
		}
	}
}
