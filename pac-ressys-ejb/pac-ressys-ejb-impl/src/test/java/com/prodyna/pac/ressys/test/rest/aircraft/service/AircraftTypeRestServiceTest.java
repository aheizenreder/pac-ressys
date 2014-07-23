/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.aircraft.service;

import java.net.URL;
import java.util.List;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.aircraft.service.AircraftTypeService;
import com.prodyna.pac.ressys.test.Deployments;
import com.prodyna.pac.ressys.util.rest.RestClientProducer;

/**
 * Tests for aircraft type rest service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class AircraftTypeRestServiceTest {

	private Logger log = Logger.getLogger(AircraftTypeRestServiceTest.class
			.getName());

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
	public void testAircraftTypeRestService() {

		log.info("START testAircraftTypeRestService() ...");
		AircraftTypeService aircraftTypeService = RestClientProducer
				.createServiceClient(deploymentURL.toString() + "rest",
						AircraftTypeService.class);

		List<AircraftType> aircraftTypeList = aircraftTypeService.getAll();
		int startListSize = aircraftTypeList.size();

		AircraftType type = new AircraftType("B373");

		type = aircraftTypeService.create(type);

		Assert.assertNotNull(
				"Id of AircraftType may not be null after persist!",
				type.getId());

		AircraftType dbAircraftType = aircraftTypeService.get(type.getId());
		Assert.assertEquals(type, dbAircraftType);

		// update aircraft name
		type.setTypeName("B343");
		dbAircraftType = aircraftTypeService.update(type);

		Assert.assertEquals(type, dbAircraftType);

		// test getAll
		List<AircraftType> newAircraftTypeList = aircraftTypeService.getAll();
		Assert.assertEquals(startListSize + 1, newAircraftTypeList.size());

		// delete aircraft
		dbAircraftType = aircraftTypeService.delete(type);
		// try to get deleted aircraft
		dbAircraftType = aircraftTypeService.get(dbAircraftType.getId());
		Assert.assertNull(dbAircraftType);

		log.info("END test()");
	}

}
