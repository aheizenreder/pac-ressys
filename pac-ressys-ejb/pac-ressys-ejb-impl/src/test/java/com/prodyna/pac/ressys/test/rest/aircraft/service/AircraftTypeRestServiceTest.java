/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.aircraft.service;

import java.net.URL;
import java.util.ArrayList;
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
	public void testInitAircraftTypeSet() {
		log.info("START testInitAircraftTypeSet() ...");

		log.info("Create AircraftTypeService from url " + deploymentURL.toString()
				+ "rest");

		AircraftTypeService aircraftTypeService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", AircraftTypeService.class);

		List<AircraftType> aircraftTypeList = aircraftTypeService.getAll();
		
		List<String> foundAircraftTypeList = new ArrayList<String>();		
		for(AircraftType existType:aircraftTypeList){
			foundAircraftTypeList.add(existType.getTypeName());
		}
		
		log.info("create aircraft type B735 ...");
		if(!foundAircraftTypeList.contains("B735")){
			AircraftType boeing735 = new AircraftType("B735");
			boeing735 = aircraftTypeService.create(boeing735);
			Assert.assertNotNull("AircraftType id may not be null after persist!",
					boeing735.getId());
		}else {
			log.info("The aircraft type B735 already exists!");
		}
		
		log.info("create aircraft type A320 ...");
		if(!foundAircraftTypeList.contains("A320")){
			AircraftType airbusA320 = new AircraftType("A320");
			airbusA320 = aircraftTypeService.create(airbusA320);
			Assert.assertNotNull("AircraftType id may not be null after persist!",
					airbusA320.getId());
		}else {
			log.info("The aircraft type A320 already exists!");
		}
		
		log.info("create aircraft type Bombardier Learjet 31A ...");
		if(!foundAircraftTypeList.contains("Bombardier Learjet 31A")){
			AircraftType learjet31A = new AircraftType("Bombardier Learjet 31A");
			learjet31A = aircraftTypeService.create(learjet31A);
			Assert.assertNotNull("AircraftType id may not be null after persist!",
					learjet31A.getId());
		}else {
			log.info("The aircraft type Bombardier Learjet 31A already exists!");
		}
		
		log.info("create aircraft type Cessna Citation XLS+ ...");
		if(!foundAircraftTypeList.contains("Cessna Citation XLS+")){
			AircraftType cessnaXLS = new AircraftType("Cessna Citation XLS+");
			cessnaXLS = aircraftTypeService.create(cessnaXLS);
			Assert.assertNotNull("AircraftType id may not be null after persist!",
					cessnaXLS.getId());
		}else {
			log.info("The aircraft type Cessna Citation XLS+ already exists!");
		}
		
		log.info("create aircraft type Cessna Citation III ...");
		if(!foundAircraftTypeList.contains("Cessna Citation III")){
			AircraftType cessna3 = new AircraftType("Cessna Citation III");
			cessna3 = aircraftTypeService.create(cessna3);
			Assert.assertNotNull("AircraftType id may not be null after persist!",
					cessna3.getId());
		}else {
			log.info("The aircraft type Cessna Citation III already exists!");
		}
		
		log.info("create aircraft type Cessna Citation Sovereign ...");
		if(!foundAircraftTypeList.contains("Cessna Citation Sovereign")){
			AircraftType cessnaSov = new AircraftType("Cessna Citation Sovereign");
			cessnaSov = aircraftTypeService.create(cessnaSov);
			Assert.assertNotNull("AircraftType id may not be null after persist!",
					cessnaSov.getId());
		}else {
			log.info("The aircraft type Cessna Citation Sovereign already exists!");
		}
		
		log.info("create aircraft type Piper Cheyenne IIIA ...");
		if(!foundAircraftTypeList.contains("Piper Cheyenne IIIA")){
			AircraftType piper3A = new AircraftType("Piper Cheyenne IIIA");
			piper3A = aircraftTypeService.create(piper3A);
			Assert.assertNotNull("AircraftType id may not be null after persist!",
					piper3A.getId());
		}else {
			log.info("The aircraft type Piper Cheyenne IIIA already exists!");
		}
		log.info("END testInitAircraftTypeSet().");
	}

	@Test
	public void testAircraftTypeRestService() {

		log.info("START testAircraftTypeRestService() ...");
		AircraftTypeService aircraftTypeService = RestClientProducer
				.createServiceClient(deploymentURL.toString() + "rest",
						AircraftTypeService.class);

		List<AircraftType> aircraftTypeList = aircraftTypeService.getAll();
		int startListSize = aircraftTypeList.size();

		AircraftType type = new AircraftType("B737");

		type = aircraftTypeService.create(type);

		Assert.assertNotNull(
				"Id of AircraftType may not be null after persist!",
				type.getId());

		AircraftType dbAircraftType = aircraftTypeService.get(type.getId());
		Assert.assertEquals(type, dbAircraftType);

		// update aircraft name
		type.setTypeName("B736");
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
