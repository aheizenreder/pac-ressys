/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.charter.service;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
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
import com.prodyna.pac.ressys.aircraft.service.AircraftService;
import com.prodyna.pac.ressys.reservation.model.Charter;
import com.prodyna.pac.ressys.reservation.model.CharterState;
import com.prodyna.pac.ressys.reservation.service.CharterService;
import com.prodyna.pac.ressys.test.Deployments;
import com.prodyna.pac.ressys.usermgmt.model.User;
import com.prodyna.pac.ressys.usermgmt.service.UserService;
import com.prodyna.pac.ressys.util.rest.RestClientProducer;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class CharterRestServiceTest {
	
	private Logger log = Logger.getLogger(CharterRestServiceTest.class
			.getName());
	
	private static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy HH:mm");

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
	public void testInitCharterSet() {
		log.info("START testInitCharterSet() ...");
		Date startDate = null;
		Date endDate = null;

		log.info("Create CharterService from url " + deploymentURL.toString()
				+ "rest");

		CharterService charterService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", CharterService.class);
		UserService userService = RestClientProducer.createServiceClient(deploymentURL.toString() + "rest", UserService.class);
		AircraftService aircraftService = RestClientProducer.createServiceClient(deploymentURL.toString() + "rest", AircraftService.class);
		
		List<Aircraft> aircraftList = aircraftService.getAll();
		
		User user = null;
		try {
			user = userService.findByLoginName("andreas");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail(e.getMessage());
		} 

		log.info("create charter for user andreas ...");
		try {
			startDate = DF.parse("25.08.2014 08:00");
			endDate = DF.parse("25.08.2014 20:00");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail(e.getMessage());
		}		
		Charter charter = new Charter(startDate, endDate, user, aircraftList.get(1), CharterState.RESERVED);
		charter = charterService.create(charter);
		Assert.assertNotNull("Id of charter may not be null after persist!", charter.getId());
		
		log.info("create charter for user andreas ...");
		try {
			startDate = DF.parse("29.08.2014 10:00");
			endDate = DF.parse("29.08.2014 23:00");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail(e.getMessage());
		}		
		charter = new Charter(startDate, endDate, user, aircraftList.get(2), CharterState.RESERVED);
		charter = charterService.create(charter);
		Assert.assertNotNull("Id of charter may not be null after persist!", charter.getId());
		
		log.info("create charter for user andreas ...");
		try {
			startDate = DF.parse("02.09.2014 10:00");
			endDate = DF.parse("02.09.2014 19:00");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail(e.getMessage());
		}		
		charter = new Charter(startDate, endDate, user, aircraftList.get(1), CharterState.RESERVED);
		charter = charterService.create(charter);
		Assert.assertNotNull("Id of charter may not be null after persist!", charter.getId());

		User userPilot = null;
		try {
			userPilot = userService.findByLoginName("pilot");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail(e.getMessage());
		}
		
		log.info("create charter for user pilot...");
		try {
			startDate = DF.parse("28.09.2014 07:00");
			endDate = DF.parse("28.09.2014 19:00");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail(e.getMessage());
		}		
		charter = new Charter(startDate, endDate, userPilot, aircraftList.get(3), CharterState.RESERVED);
		charter = charterService.create(charter);
		Assert.assertNotNull("Id of charter may not be null after persist!", charter.getId());

		log.info("END testInitCharterSet().");
	}

	@Test
	public void testCharterRestService() {
		log.info("START test charter service...");

		CharterService charterService = RestClientProducer.createServiceClient(deploymentURL.toString() + "rest", CharterService.class);

		AircraftService aircraftService = RestClientProducer.createServiceClient(deploymentURL.toString() + "rest", AircraftService.class);
		
		UserService userService = RestClientProducer.createServiceClient(deploymentURL.toString() + "rest", UserService.class);
		
		List<Aircraft> aircraftList = aircraftService.getAll();
		int startListSize = aircraftList.size();
		User user = null;
		try {
			user = userService.findByLoginName("andreas");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail(e.getMessage());
		} 
		Date startDate = null;
		Date endDate = null;
		Date updateStartDate = null;
		try {
			startDate = DF.parse("22.08.2014 08:00");
			endDate = DF.parse("22.08.2014 20:00");
			updateStartDate = DF.parse("22.08.2014 10:00");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail(e.getMessage());
		}
		
		Charter charter = new Charter(startDate, endDate, user, aircraftList.get(0), CharterState.RESERVED);
		//test create
		charter = charterService.create(charter);
		Assert.assertNotNull("Id of charter may not be null after persist!",
		charter.getId());
		
		//test get
		Charter dbCharter = charterService.get(charter.getId());
		Assert.assertEquals(charter, dbCharter);
		
		// update charter startDate
		charter.setStartDate(updateStartDate);
		dbCharter = charterService.update(charter);
		Assert.assertEquals(charter, dbCharter);
		
		// test getAll
		List<Charter> newCharterList = charterService.getAll();
		Assert.assertEquals(startListSize + 1, newCharterList.size());

		// test delete charter
		dbCharter = charterService.delete(charter);
		// try to get deleted charter
		dbCharter = charterService.get(dbCharter.getId());
		Assert.assertNull(dbCharter);

		log.info("END test charter service...");
	}

}
