/**
 * 
 */
package com.prodyna.pac.ressys.test;

import java.io.File;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * Creator for test archive for arquillian tests.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class Deployments {

	public static Archive<?> createDeployment() {

		File[] modules = Maven.resolver()
				.loadPomFromFile("../../pac-ressys-ear/pom.xml")
				.resolve("com.prodyna.pac.ressys:pac-ressys-ejb-impl",
						"com.prodyna.pac.ressys:pac-ressys-web:war:?")
				.withoutTransitivity().asFile();

		File[] libs = Maven
				.resolver()
				.loadPomFromFile("../../pac-ressys-ear/pom.xml")
				.resolve("com.prodyna.pac.ressys:pac-ressys-ejb-intf",
						"com.prodyna.pac.ressys:pac-ressys-persistence")
				.withoutTransitivity().asFile();

		EnterpriseArchive ressysEar = ShrinkWrap.create(
				EnterpriseArchive.class, "pac-ressys-ear.ear");
		ressysEar.addAsLibraries(libs);
		ressysEar.addAsModules(modules);
		ressysEar.setApplicationXML("application.xml");

		return ressysEar;
	}
}
