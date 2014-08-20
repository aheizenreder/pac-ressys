/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.aircraft.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.aircraft.service.AircraftService;
import com.prodyna.pac.ressys.aircraft.service.AircraftTypeService;
import com.prodyna.pac.ressys.basis.exception.CreateFailedException;
import com.prodyna.pac.ressys.basis.exception.DeleteFailedException;
import com.prodyna.pac.ressys.basis.exception.NotFoundException;
import com.prodyna.pac.ressys.basis.exception.UpdateFailedException;
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

	private AircraftTypeService aircraftTypeService;

	private AircraftService aircraftService;

	private AircraftType testType;

	private Aircraft testAircraft;

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
		// get aircraft type service instance
		aircraftTypeService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", AircraftTypeService.class);

		// get aircraft service
		aircraftService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", AircraftService.class);

		// create test aircraft type
		testType = new AircraftType("B373");

		testType = aircraftTypeService.create(testType);
		Assert.assertNotNull(
				"Id of AircraftType may not be null after persist!",
				testType.getId());

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		// delete aircraft type created for test
		if (testType != null && testType.getId() != null) {
			aircraftTypeService.delete(testType);
		}
	}

	@Test
	public void testInitAircraftSet() throws Exception {
		log.info("START testInitAircraftSet() ...");

		log.info("Create AircraftService from url " + deploymentURL.toString()
				+ "rest");

		List<AircraftType> aircraftTypeList = aircraftTypeService.getAll();
		HashMap<String, AircraftType> aircraftTypeMap = new HashMap<String, AircraftType>();
		for (AircraftType existType : aircraftTypeList) {
			aircraftTypeMap.put(existType.getTypeName(), existType);
		}

		List<Aircraft> aircraftList = aircraftService.getAll();

		List<String> foundAircraftList = new ArrayList<String>();
		for (Aircraft existAircraft : aircraftList) {
			foundAircraftList.add(existAircraft.getAircraftName());
		}

		log.info("create aircraft D-KPRT...");
		if (!foundAircraftList.contains("D-KPRT")) {
			AircraftType boeing735 = aircraftTypeMap.get("B735");
			Aircraft dKprt = new Aircraft(boeing735, "D-KPRT");
			dKprt = aircraftService.create(dKprt);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dKprt.getId());
		} else {
			log.info("The aircraft D-KPRT already exists!");
		}

		log.info("create aircraft D-KZYO...");
		if (!foundAircraftList.contains("D-KZYO")) {
			AircraftType boeing735 = aircraftTypeMap.get("B735");
			Aircraft dKzyo = new Aircraft(boeing735, "D-KZYO");
			dKzyo = aircraftService.create(dKzyo);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dKzyo.getId());
		} else {
			log.info("The aircraft D-KZYO already exists!");
		}

		log.info("create aircraft D-ASFC...");
		if (!foundAircraftList.contains("D-ASFC")) {
			AircraftType airbusA320 = aircraftTypeMap.get("A320");
			Aircraft dAsfc = new Aircraft(airbusA320, "D-ASFC");
			dAsfc = aircraftService.create(dAsfc);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dAsfc.getId());
		} else {
			log.info("The aircraft D-ASFC already exists!");
		}

		log.info("create aircraft D-APYS...");
		if (!foundAircraftList.contains("D-APYS")) {
			AircraftType airbusA320 = aircraftTypeMap.get("A320");
			Aircraft dApys = new Aircraft(airbusA320, "D-APYS");
			dApys = aircraftService.create(dApys);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dApys.getId());
		} else {
			log.info("The aircraft D-APYS already exists!");
		}

		log.info("create aircraft D-CPRO...");
		if (!foundAircraftList.contains("D-CPRO")) {
			AircraftType bombardier31A = aircraftTypeMap
					.get("Bombardier Learjet 31A");
			Aircraft dCpro = new Aircraft(bombardier31A, "D-CPRO");
			dCpro = aircraftService.create(dCpro);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dCpro.getId());
		} else {
			log.info("The aircraft D-CPRO already exists!");
		}

		log.info("create aircraft D-CCLA...");
		if (!foundAircraftList.contains("D-CCLA")) {
			AircraftType cessnaXLS = aircraftTypeMap
					.get("Cessna Citation XLS+");
			Aircraft dCcla = new Aircraft(cessnaXLS, "D-CCLA");
			dCcla = aircraftService.create(dCcla);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dCcla.getId());
		} else {
			log.info("The aircraft D-CCLA already exists!");
		}

		log.info("create aircraft D-CREY...");
		if (!foundAircraftList.contains("D-CREY")) {
			AircraftType cessna3 = aircraftTypeMap.get("Cessna Citation III");
			Aircraft dCrey = new Aircraft(cessna3, "D-CREY");
			dCrey = aircraftService.create(dCrey);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dCrey.getId());
		} else {
			log.info("The aircraft D-CREY already exists!");
		}

		log.info("create aircraft D-CMDH...");
		if (!foundAircraftList.contains("D-CMDH")) {
			AircraftType cessnaSov = aircraftTypeMap
					.get("Cessna Citation Sovereign");
			Aircraft dCmdh = new Aircraft(cessnaSov, "D-CMDH");
			dCmdh = aircraftService.create(dCmdh);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dCmdh.getId());
		} else {
			log.info("The aircraft D-CMDH already exists!");
		}

		log.info("create aircraft D-IFSH...");
		if (!foundAircraftList.contains("D-IFSH")) {
			AircraftType piper3A = aircraftTypeMap.get("Piper Cheyenne IIIA");
			Aircraft dIfsh = new Aircraft(piper3A, "D-IFSH");
			dIfsh = aircraftService.create(dIfsh);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dIfsh.getId());
		} else {
			log.info("The aircraft D-IFSH already exists!");
		}

		log.info("END testInitAircraftSet().");
	}

	@Test
	public void testAircraftRestService() {

		log.info("START test aircraft service ...");

		Exception expectedException = null;

		List<Aircraft> aircraftList = aircraftService.getAll();
		int startListSize = aircraftList.size();

		Aircraft aircraft = new Aircraft(testType, "D-LHAD");

		try {
			aircraft = aircraftService.create(aircraft);
		} catch (CreateFailedException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail("Unexpected exception: " + e.getMessage());
		}

		Assert.assertNotNull("Id of Aircraft may not be null after persist!",
				aircraft.getId());

		Aircraft dbAircraft = null;
		try {
			dbAircraft = aircraftService.get(aircraft.getId());
		} catch (NotFoundException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
		Assert.assertEquals(aircraft, dbAircraft);

		// update aircraft name
		aircraft.setAircraftName("D-LHDA");
		try {
			dbAircraft = aircraftService.update(aircraft);
		} catch (UpdateFailedException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail("Unexpected exception:" + e.getMessage());
		}

		Assert.assertEquals(aircraft, dbAircraft);

		// test getAll
		List<Aircraft> newAircraftList = aircraftService.getAll();
		Assert.assertEquals(startListSize + 1, newAircraftList.size());

		// delete aircraft
		try {
			dbAircraft = aircraftService.delete(aircraft);
		} catch (DeleteFailedException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
		// try to get deleted aircraft
		try {
			dbAircraft = aircraftService.get(dbAircraft.getId());
		} catch (Exception e) {
			expectedException = e;
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		Assert.assertNotNull(
				"Missing exception by request an delete for not persisted aircraft!",
				expectedException);

		log.info("END test()");
	}

	@Test
	public void testAicraftServiceExceptions() {
		log.info("START testAicraftServiceExceptions() ...");
		Assert.assertNotNull("Id of aircraft type may not be null!",
				testType.getId());

		// try to get not existing Aircraft
		Exception expectedException = null;
		try {
			testAircraft = aircraftService.get(Long.valueOf(0));
			log.info("got an aircraft with id 0!");
		} catch (Exception e) {
			expectedException = e;
			log.log(Level.SEVERE, "Expected exception: " + e.getMessage(), e);
		}
		Assert.assertNotNull(
				"Missing exception by request aircraft with not existing id!",
				expectedException);

		// reset expectedException to null for next check.
		expectedException = null;
		// create a new aircraft for test
		testAircraft = new Aircraft(testType, "D-TEST");
		// try to update with not created aircraft
		try {
			aircraftService.update(testAircraft);
		} catch (Exception e) {
			expectedException = e;
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		Assert.assertNotNull(
				"Missing exception by request an update for not persisted aircraft!",
				expectedException);

		// reset expectedException to null for next check.
		expectedException = null;
		// test for exception by delete
		try {
			aircraftService.delete(testAircraft);
		} catch (Exception e) {
			expectedException = e;
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		Assert.assertNotNull(
				"Missing exception by request an delete for not persisted aircraft!",
				expectedException);
		log.info("END testAicraftServiceExceptions().");
	}
}
