/**
 * 
 */
package com.prodyna.pac.ressys.basis.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prodyna.pac.ressys.basis.security.AdminAccessOnly;
import com.prodyna.pac.ressys.basis.security.AllAccess;
import com.prodyna.pac.ressys.basis.security.Secured;
import com.prodyna.pac.ressys.monitoring.logging.Logged;

/**
 * This interface represents basis interface for all ressys services. It defines
 * the CRUD operations.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Logged
@Secured
public interface BasisRessysService<T> {

	/**
	 * persists the entity in the database.
	 * 
	 * @param entity
	 *            the entity object to persists in the database.
	 * @return the with key information enriched entity object.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public T create(T entity);

	/**
	 * reads the entity identified by id from the database.
	 * 
	 * @param id
	 *            Id of the entity, which is to read.
	 * 
	 * @return from database readed entity object.
	 */
	@GET
	@Path("/{id:[0-9]+}")
	@AllAccess
	public T get(@PathParam("id") Long id);

	/**
	 * reads all entities from database.
	 * 
	 * @return a List of entities.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public List<T> getAll();

	/**
	 * persists changes on the entity object in the database.
	 * 
	 * @param entity
	 *            the entity object with updated properties, which are to
	 *            persist.
	 * 
	 * @return the updated entity object.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public T update(T entity);

	/**
	 * deletes by the entity represented object from database.
	 * 
	 * @param entity
	 *            object to delete.
	 * 
	 * @return updated entity object after delete.
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public T delete(T entity);

}
