/**
 * 
 */
package com.prodyna.pac.ressys.test.rest.usermgmt.service;

import java.security.MessageDigest;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test Case for working on password encryption.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class EncryptPassowordTest {

	private Logger log = Logger.getLogger(EncryptPassowordTest.class.getName());

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testEncyrptPassword() throws Exception {
		log.info("START testEncyrptPassword() ...");
		String password = "TestPassword";
		String sha256MySQL = "7bcf9d89298f1bfae16fa02ed6b61908fd2fa8de45dd8e2153a3c47300765328";

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// encode password in base64
		byte[] digestPassword = md.digest(password.getBytes());
		String digestPasswordBase64 = DatatypeConverter.printHexBinary(digestPassword).toLowerCase();
		
		log.info("diges from password: " + digestPasswordBase64);
		
		Assert.assertEquals(sha256MySQL, digestPasswordBase64);

		log.info("END testEncyrptPassword().");
	}

}
