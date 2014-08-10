/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.aircraft.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
	public void testInitAircraftSet() {
		log.info("START testInitAircraftSet() ...");

		log.info("Create AircraftService from url " + deploymentURL.toString()
				+ "rest");
		
		AircraftTypeService aircraftTypeService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", AircraftTypeService.class);

		List<AircraftType> aircraftTypeList = aircraftTypeService.getAll();
		HashMap<String, AircraftType> aircraftTypeMap = new HashMap<String, AircraftType>();
		for(AircraftType existType:aircraftTypeList){
			aircraftTypeMap.put(existType.getTypeName(), existType);
		}

		AircraftService aircraftService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", AircraftService.class);

		List<Aircraft> aircraftList = aircraftService.getAll();
		
		List<String> foundAircraftList = new ArrayList<String>();		
		for(Aircraft existAircraft:aircraftList){
			foundAircraftList.add(existAircraft.getAircraftName());
		}
		
		log.info("create aircraft D-KPRT...");
		if(!foundAircraftList.contains("D-KPRT")){
			AircraftType boeing735 = aircraftTypeMap.get("B735");
			Aircraft dKprt = new Aircraft(boeing735, "D-KPRT");
			dKprt = aircraftService.create(dKprt);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dKprt.getId());
		}else {
			log.info("The aircraft D-KPRT already exists!");
		}
		
		log.info("create aircraft D-KZYO...");
		if(!foundAircraftList.contains("D-KZYO")){
			AircraftType boeing735 = aircraftTypeMap.get("B735");
			Aircraft dKzyo = new Aircraft(boeing735, "D-KZYO");
			dKzyo = aircraftService.create(dKzyo);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dKzyo.getId());
		}else {
			log.info("The aircraft D-KZYO already exists!");
		}
		
		log.info("create aircraft D-ASFC...");
		if(!foundAircraftList.contains("D-ASFC")){
			AircraftType airbusA320 = aircraftTypeMap.get("A320");
			Aircraft dAsfc = new Aircraft(airbusA320, "D-ASFC");
			dAsfc = aircraftService.create(dAsfc);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dAsfc.getId());
		}else {
			log.info("The aircraft D-ASFC already exists!");
		}
		
		log.info("create aircraft D-APYS...");
		if(!foundAircraftList.contains("D-APYS")){
			AircraftType airbusA320 = aircraftTypeMap.get("A320");
			Aircraft dApys = new Aircraft(airbusA320, "D-APYS");
			dApys = aircraftService.create(dApys);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dApys.getId());
		}else {
			log.info("The aircraft D-APYS already exists!");
		}
		
		log.info("create aircraft D-CPRO...");
		if(!foundAircraftList.contains("D-CPRO")){
			AircraftType bombardier31A = aircraftTypeMap.get("Bombardier Learjet 31A");
			Aircraft dCpro = new Aircraft(bombardier31A, "D-CPRO");
			dCpro = aircraftService.create(dCpro);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dCpro.getId());
		}else {
			log.info("The aircraft D-CPRO already exists!");
		}
		
		log.info("create aircraft D-CCLA...");
		if(!foundAircraftList.contains("D-CCLA")){
			AircraftType cessnaXLS = aircraftTypeMap.get("Cessna Citation XLS+");
			Aircraft dCcla = new Aircraft(cessnaXLS, "D-CCLA");
			dCcla = aircraftService.create(dCcla);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dCcla.getId());
		}else {
			log.info("The aircraft D-CCLA already exists!");
		}
		
		log.info("create aircraft D-CREY...");
		if(!foundAircraftList.contains("D-CREY")){
			AircraftType cessna3 = aircraftTypeMap.get("Cessna Citation III");
			Aircraft dCrey = new Aircraft(cessna3, "D-CREY");
			dCrey = aircraftService.create(dCrey);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dCrey.getId());
		}else {
			log.info("The aircraft D-CREY already exists!");
		}
		
		log.info("create aircraft D-CMDH...");
		if(!foundAircraftList.contains("D-CMDH")){
			AircraftType cessnaSov = aircraftTypeMap.get("Cessna Citation Sovereign");
			Aircraft dCmdh = new Aircraft(cessnaSov, "D-CMDH");
			dCmdh = aircraftService.create(dCmdh);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dCmdh.getId());
		}else {
			log.info("The aircraft D-CMDH already exists!");
		}
		
		log.info("create aircraft D-IFSH...");
		if(!foundAircraftList.contains("D-IFSH")){
			AircraftType piper3A = aircraftTypeMap.get("Piper Cheyenne IIIA");
			Aircraft dIfsh = new Aircraft(piper3A, "D-IFSH");
			dIfsh = aircraftService.create(dIfsh);
			Assert.assertNotNull("Aircraft id may not be null after persist!",
					dIfsh.getId());
		}else {
			log.info("The aircraft D-IFSH already exists!");
		}

		log.info("END testInitAircraftSet().");
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
