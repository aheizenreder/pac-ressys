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

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.aircraft.service.AircraftService;
import com.prodyna.pac.ressys.aircraft.service.AircraftTypeService;
import com.prodyna.pac.ressys.test.Deployments;
import com.prodyna.pac.ressys.util.rest.RestClientProducer;

/**
 * Test for rest aircraft service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class AircraftRestServiceTest {

	private Logger log = Logger.getLogger(AircraftRestServiceTest.class
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
	public void testAircraftRestService() {

		log.info("START test aircraft service ...");

		AircraftService aircraftService = RestClientProducer.createServiceClient(deploymentURL.toString() + "rest", AircraftService.class);

		AircraftTypeService aircraftTypeService = RestClientProducer.createServiceClient(deploymentURL.toString() + "rest", AircraftTypeService.class);

		AircraftType type = new AircraftType("B373");

		type = aircraftTypeService.create(type);
		Assert.assertNotNull(
				"Id of AircraftType may not be null after persist!",
				type.getId());

		List<Aircraft> aircraftList = aircraftService.getAll();
		int startListSize = aircraftList.size();

		Aircraft aircraft = new Aircraft(type, "D-LHAD");

		aircraft = aircraftService.create(aircraft);

		Assert.assertNotNull("Id of Aircraft may not be null after persist!",
				aircraft.getId());

		Aircraft dbAircraft = aircraftService.get(aircraft.getId());
		Assert.assertEquals(aircraft, dbAircraft);

		// update aircraft name
		aircraft.setAircraftName("D-LHDA");
		dbAircraft = aircraftService.update(aircraft);

		Assert.assertEquals(aircraft, dbAircraft);

		// test getAll
		List<Aircraft> newAircraftList = aircraftService.getAll();
		Assert.assertEquals(startListSize + 1, newAircraftList.size());

		// delete aircraft
		dbAircraft = aircraftService.delete(aircraft);
		// try to get deleted aircraft
		dbAircraft = aircraftService.get(dbAircraft.getId());
		Assert.assertNull(dbAircraft);

		aircraftTypeService.delete(type);

		log.info("END test()");
	}

}
