/**
 * 
 */
package com.prodyna.pac.ressys.test;

import java.io.File;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * Creator for test archive for arquillian tests.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class Deployments {

	public static Archive<?> createDeployment() {

		File[] libs = Maven
				.resolver()
				.loadPomFromFile("../../pac-ressys-ear/pom.xml")
				.resolve("com.prodyna.pac.ressys:pac-ressys-ejb-intf")
				.withoutTransitivity().asFile();


		WebArchive ressysEar = ShrinkWrap.create(
				WebArchive.class, "pac-ressys-test.war");
		ressysEar.addPackages(true, "com/prodyna/pac/ressys");
		ressysEar.deletePackages(true, "com/prodyna/pac/ressys/test/rest");
		ressysEar.addAsResource(new File("../pac-ressys-persistence/src/main/resources/META-INF/persistence.xml"),"META-INF/persistence.xml");
		ressysEar.addAsWebInfResource(new File("src/main/resources/META-INF/beans.xml"), "beans.xml");
		ressysEar.addAsLibraries(libs);

		return ressysEar;
	}
}
