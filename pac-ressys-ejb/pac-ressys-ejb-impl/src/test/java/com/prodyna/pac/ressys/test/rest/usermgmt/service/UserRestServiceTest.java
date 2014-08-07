/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.usermgmt.service;

import java.net.URL;
import java.util.Calendar;
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

import com.prodyna.pac.ressys.test.Deployments;
import com.prodyna.pac.ressys.usermgmt.model.Role;
import com.prodyna.pac.ressys.usermgmt.model.User;
import com.prodyna.pac.ressys.usermgmt.service.RoleService;
import com.prodyna.pac.ressys.usermgmt.service.UserService;
import com.prodyna.pac.ressys.util.rest.RestClientProducer;

/**
 * Unit test for User Service by its REST interface.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class UserRestServiceTest {

	private static Logger log = Logger.getLogger(UserRestServiceTest.class
			.getName());

	private Role guestRole;
	private Role adminRole;
	private Role userRole;

	@ArquillianResource
	private static URL deploymentURL;

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
		RoleService roleService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", RoleService.class);
		List<Role> roleList = roleService.getAll();
		if (roleList != null && !roleList.isEmpty()) {
			for (Role currentRole : roleList) {
				String currentRoleName = currentRole.getRole();
				if ("Guest".equals(currentRoleName)) {
					log.info("Found guest role in role list");
					guestRole = currentRole;
				} else if ("User".equals(currentRoleName)) {
					log.info("Found user role in role list");
					userRole = currentRole;
				} else if ("Admin".equals(currentRoleName)) {
					log.info("Found admin role in role list");
					adminRole = currentRole;
				} else {
					log.info("Unknown role " + currentRoleName
							+ " found in role list!");
				}
			}
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitUserSet() {
		log.info("START testInitUserSet() ...");
		boolean roleAssigned = false;

		Exception unexpectedException = null;

		log.info("Create UserService from url " + deploymentURL.toString()
				+ "rest");

		UserService userService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", UserService.class);

		List<User> startUserList = userService.getAll();
		int startUserListSize = startUserList.size();
		log.info("start user list size " + startUserListSize);

		log.info("create user guest ...");
		User existingGuestUser = null;
		try {
			existingGuestUser = userService.findByLoginName("guest");
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"User guest does not exist: " + e.getMessage(), e);
		}
		if (existingGuestUser == null) {
			// guest user does not exist
			// --> create one
			User guestUser = new User("Guest", "guest", "guestGuest",
					"guest@example.com", null, null);
			guestUser.setPasswordEncrypted(false);

			guestUser = userService.create(guestUser);
			Assert.assertNotNull("User id may not be null after persist!",
					guestUser.getId());

			log.info("assign guest role to guest user ...");
			try {
				roleAssigned = userService.addRole(guestUser.getId(),
						guestRole.getId());
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage(), e);
				unexpectedException = e;
			}
			Assert.assertNull("Unexpected exception was thrown!",
					unexpectedException);
			Assert.assertTrue("Guest role was not assigned to guest user!",
					roleAssigned);
		} else {
			log.info("The user guest already exists!");
		}

		log.info("create user andreas");
		User existingAndreasUser = null;
		try {
			existingAndreasUser = userService.findByLoginName("andreas");
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"user andreas was not found: " + e.getMessage(), e);
		}

		if (existingAndreasUser == null) {
			// guest user does not exist
			// --> create one
			Calendar licenceValidDateCalendar = Calendar.getInstance();
			licenceValidDateCalendar.set(2024, Calendar.MAY, 28, 0, 0, 0);
			licenceValidDateCalendar.set(Calendar.MILLISECOND, 0);

			User andreasUser = new User("Andreas", "andreas", "andreasAndreas",
					"aheizenreder@prodyna.com", "AH270578",
					licenceValidDateCalendar.getTime());
			andreasUser.setPasswordEncrypted(false);

			andreasUser = userService.create(andreasUser);
			Assert.assertNotNull("User id may not be null after persist!",
					andreasUser.getId());

			log.info("assign admin role to user andreas ...");
			try {
				roleAssigned = userService.addRole(andreasUser.getId(),
						adminRole.getId());
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage(), e);
				unexpectedException = e;
			}
			Assert.assertNull("Unexpected exception was thrown!",
					unexpectedException);
			Assert.assertTrue("Admin role was not assigned to user andreas!",
					roleAssigned);
		} else {
			log.info("The user andreas already exists!");
		}

		log.info("create user pilot ...");
		User existingPilotUser = null;
		try {
			existingPilotUser = userService.findByLoginName("pilot");
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"User pilot does not exists: " + e.getMessage(), e);
		}

		if (existingPilotUser == null) {
			// guest user does not exist
			// --> create one
			Calendar licenceValidDateCalendar = Calendar.getInstance();
			licenceValidDateCalendar.set(2020, Calendar.NOVEMBER, 30, 0, 0, 0);
			licenceValidDateCalendar.set(Calendar.MILLISECOND, 0);

			User pilotUser = new User("Pilot", "pilot", "pilotPilot",
					"pilot@example.com", "PL011181",
					licenceValidDateCalendar.getTime());
			pilotUser.setPasswordEncrypted(false);

			pilotUser = userService.create(pilotUser);
			Assert.assertNotNull("User id may not be null after persist!",
					pilotUser.getId());

			log.info("assign user role to user pilot ...");
			try {
				roleAssigned = userService.addRole(pilotUser.getId(),
						userRole.getId());
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage(), e);
				unexpectedException = e;
			}
			Assert.assertNull("Unexpected exception was thrown!",
					unexpectedException);
			Assert.assertTrue("User role was not assigned to user pilot!",
					roleAssigned);
		} else {
			log.info("The user pilot already exists!");
		}

		log.info("END testInitUserSet().");
	}

	@Test
	public void testUserRestService() throws Exception {
		log.info("START testUserRestService() ...");

		boolean roleAssigned = false;

		log.info("Create UserService from url " + deploymentURL.toString()
				+ "rest");

		UserService userService = RestClientProducer.createServiceClient(
				deploymentURL.toString() + "rest", UserService.class);

		// try to clean up before running test
		// if last run was not able to delete created objects
		// try to get them and remove
		try {
			User oldUser = userService.findByLoginName("test");
			if (oldUser != null) {
				// old user is still there
				// get possible Role assignments
				List<Role> oldRolesList = userService.getRoles(oldUser.getId());
				for (Role oldRole : oldRolesList) {
					boolean oldRoleAssgnmentRemoved = userService.removeRole(
							oldUser.getId(), oldRole.getId());
					Assert.assertTrue("Old role " + oldRole.getRole()
							+ " can not be removed from assignment!",
							oldRoleAssgnmentRemoved);
				}
				// delete the user
				userService.delete(oldUser);
			}
		} catch (Exception e) {
			log.log(Level.INFO, "user for clean up not found.", e);
		}

		List<User> startUserList = userService.getAll();
		int startUserListSize = startUserList.size();
		log.info("start user list size " + startUserListSize);

		log.info("create test user ...");
		User testUser = new User("Test", "test", "testTest",
				"test@example.com", null, null);
		testUser = userService.create(testUser);
		Assert.assertNotNull("User id may not be null after persist!",
				testUser.getId());

		log.info("assign guest role to test user ...");
		try {
			roleAssigned = userService.addRole(testUser.getId(),
					guestRole.getId());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail("Exception by setting role to user!");
		}

		Assert.assertTrue("Guest role was not assigned to guest user!",
				roleAssigned);

		log.info("try to assign guest role to test user again ...");
		Exception expectedException = null;
		roleAssigned = false;
		try {
			roleAssigned = userService.addRole(testUser.getId(),
					guestRole.getId());
		} catch (Exception e) {
			log.info("Expected exception RoleAlreadyAssignedException was catched as expected: "
					+ e.getMessage());
			expectedException = e;
		}
		Assert.assertNotNull("An exception was expected!", expectedException);
		Assert.assertFalse("Because the role is already assigned!",
				roleAssigned);

		log.info("try to add second role to the test user");
		roleAssigned = false;
		try {
			roleAssigned = userService.addRole(testUser.getId(),
					userRole.getId());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			Assert.fail("Unexpected exception while add second role to test user: "
					+ e.getMessage());
		}
		Assert.assertTrue("User role was not assigned to the test user!",
				roleAssigned);

		log.info("get user by its id ...");
		User userById = userService.get(testUser.getId());
		Assert.assertEquals("User found by id is not equal!", testUser,
				userById);
		Assert.assertTrue("Wrong value in isPasswordEncrypt!",
				userById.isPasswordEncrypted());

		log.info("update users name ...");
		testUser.setUserName("Test Test");
		userService.update(testUser);
		userById = userService.get(testUser.getId());
		Assert.assertTrue("User not equals after update",
				testUser.equals(userById));

		log.info("get user by its login name ...");
		User userByLoginName = userService.findByLoginName(testUser
				.getLoginName());
		Assert.assertEquals("User found by login name is not equal!", testUser,
				userByLoginName);
		Assert.assertTrue("Wrong value in isPasswordEncrypt!",
				userById.isPasswordEncrypted());

		log.info("try to get user by its login name and password (encrypted) ...");
		User userByLoginAndPassword = userService.findUser(
				testUser.getLoginName(), testUser.getPassword(),
				testUser.isPasswordEncrypted());
		Assert.assertEquals(
				"User found by login and password is not the same!", testUser,
				userByLoginAndPassword);
		Assert.assertTrue("Wrong value in isPasswordEncrypt!",
				userByLoginAndPassword.isPasswordEncrypted());

		log.info("try to get user by its login name and password (plane) ...");
		User userByLoginAndPasswordPlane = userService.findUser(
				testUser.getLoginName(), "testTest", false);
		Assert.assertEquals(
				"User found by login and plane password is not the same!",
				testUser, userByLoginAndPasswordPlane);
		Assert.assertTrue("Wrong value in isPasswordEncrypt!",
				userByLoginAndPasswordPlane.isPasswordEncrypted());

		log.info("get all roles for user");
		List<Role> userRoleList = userService.getRoles(testUser.getId());
		Assert.assertEquals("Wrong number of roles!", 2, userRoleList.size());

		log.info("get all users ...");
		List<User> userList = userService.getAll();
		Assert.assertEquals("Wrong number of user in list!",
				startUserListSize + 1, userList.size());

		log.info("remove roles from user ...");
		for (Role role : userRoleList) {
			boolean roleRemoved = userService.removeRole(testUser.getId(),
					role.getId());
			Assert.assertTrue("Role" + role.getRole() + " can not be removed!",
					roleRemoved);
		}

		log.info("delete test user ...");
		userService.delete(testUser);
		User deletedUser = userService.get(testUser.getId());
		Assert.assertNull("User have to be null in the database!", deletedUser);

		log.info("END testUserRestService().");
	}
}
