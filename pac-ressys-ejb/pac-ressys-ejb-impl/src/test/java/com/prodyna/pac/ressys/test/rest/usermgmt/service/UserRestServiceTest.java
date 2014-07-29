/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.usermgmt.service;

import java.net.URL;
import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.pac.ressys.test.Deployments;

/**
 * Unit test for User Service by its REST interface.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class UserRestServiceTest {

	private Logger log = Logger.getLogger(UserRestServiceTest.class.getName());

	@ArquillianResource
	private URL deploymentURL;

	@Deployment(testable = false)
	public static Archive<?> createDeployment() {
		return Deployments.createDeployment();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}


	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserRestService() {
		log.info("START testUserRestService() ...");
		
		log.info("END testUserRestService().");
	}

}
