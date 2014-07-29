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

		log.info("START test role service ...");

		RoleService roleService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", RoleService.class);

		List<Role> roleList = roleService.getAll();
		int startListSize = roleList.size();

		// crate Admin role
		Role adminRole = new Role("Admin");
		adminRole = roleService.create(adminRole);
		Assert.assertNotNull("Id of Role may not be null after persist!",
				adminRole.getId());

		// create User role
		Role userRole = new Role("User");
		userRole = roleService.create(userRole);

		Assert.assertNotNull("Id of Role may not be null after persist!",
				userRole.getId());

		// create Guest Role
		Role guestRole = new Role("Guest");
		guestRole = roleService.create(guestRole);

		Role dbRole = roleService.get(userRole.getId());
		Assert.assertEquals(userRole, dbRole);

		// test getAll
		List<Role> newRoleList = roleService.getAll();
		Assert.assertEquals(startListSize + 3, newRoleList.size());

		log.info("END test role service.");
	}

}
