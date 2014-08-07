/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.usermgmt.service;

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

import com.prodyna.pac.ressys.test.Deployments;
import com.prodyna.pac.ressys.usermgmt.model.Role;
import com.prodyna.pac.ressys.usermgmt.service.RoleService;
import com.prodyna.pac.ressys.util.rest.RestClientProducer;

/**
 * Unit test for Role Service by rest.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class RoleRestServiceTest {

	private Logger log = Logger.getLogger(RoleRestServiceTest.class.getName());

	private RoleService roleService;

	@ArquillianResource
	private URL deploymentURL;

	private int startListSize;

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

		roleService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", RoleService.class);

		List<Role> roleList = roleService.getAll();
		startListSize = roleList.size();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testInitAircraftRestService() {
//		log.info("START testInitAircraftRestService() ...");
//
//		Assert.assertNotNull("RoleService instance is null!", roleService);
//
//		// crate Admin role
//		Role adminRole = new Role("Admin");
//		adminRole = roleService.create(adminRole);
//		Assert.assertNotNull("Id of Role may not be null after persist!",
//				adminRole.getId());
//
//		// create User role
//		Role userRole = new Role("User");
//		userRole = roleService.create(userRole);
//
//		Assert.assertNotNull("Id of Role may not be null after persist!",
//				userRole.getId());
//
//		// create Guest Role
//		Role guestRole = new Role("Guest");
//		guestRole = roleService.create(guestRole);
//
//		Role dbRole = roleService.get(userRole.getId());
//		Assert.assertEquals(userRole, dbRole);
//
//		// test getAll
//		List<Role> newRoleList = roleService.getAll();
//		Assert.assertEquals(startListSize + 3, newRoleList.size());
//
//		log.info("END testInitAircraftRestService().");
//	}

	@Test
	public void testAircraftRestService() {

		log.info("START test role service ...");

		// create Test role
		Role testRole = new Role("Test");
		testRole = roleService.create(testRole);
		Assert.assertNotNull("Id of Role may not be null after persist!",
				testRole.getId());

		Role dbRole = roleService.get(testRole.getId());
		Assert.assertEquals(testRole, dbRole);

		testRole.setRole("Test1");
		roleService.update(testRole);
		dbRole = roleService.get(testRole.getId());
		Assert.assertEquals("Role is not the same after update!", testRole,
				dbRole);

		// test getAll
		List<Role> newRoleList = roleService.getAll();
		Assert.assertEquals(startListSize + 1, newRoleList.size());

		log.info("END test role service.");
	}

}
