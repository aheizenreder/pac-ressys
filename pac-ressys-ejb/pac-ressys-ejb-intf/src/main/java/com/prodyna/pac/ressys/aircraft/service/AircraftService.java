/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.service;

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

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.basis.exception.CreateFailedException;
import com.prodyna.pac.ressys.basis.exception.DeleteFailedException;
import com.prodyna.pac.ressys.basis.exception.NotFoundException;
import com.prodyna.pac.ressys.basis.exception.UpdateFailedException;
import com.prodyna.pac.ressys.basis.security.AdminAccessOnly;
import com.prodyna.pac.ressys.basis.security.AllAccess;
import com.prodyna.pac.ressys.basis.security.Secured;

/**
 * Interface for service on Aircraft entity.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Secured
@Path("/aircraft")
public interface AircraftService {

	/**
	 * persists the aircraft in the database.
	 * 
	 * @param aircraft
	 *            the aircraft object to persists in the database.
	 * @return the with key information enriched aircraft object.
	 * @throws CreateFailedException
	 *             if errors occurs by creation of the element in the database.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Aircraft create(Aircraft aircraft) throws CreateFailedException;

	/**
	 * reads the aircraft identified by id from the database.
	 * 
	 * @param id
	 *            Id of the aircraft, which is to read.
	 * 
	 * @return from database read aircraft object.
	 * @throws NotFoundException
	 *             if errors occurs by look up for an element by its id.
	 */
	@GET
	@Path("/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public Aircraft get(@PathParam("id") Long id) throws NotFoundException;

	/**
	 * reads all aircraft from database.
	 * 
	 * @return a List of entities.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public List<Aircraft> getAll();

	/**
	 * persists changes on the aircraft object in the database.
	 * 
	 * @param aircraft
	 *            the aircraft object with updated properties, which are to
	 *            persist.
	 * 
	 * @return the updated aircraft object.
	 * @throws UpdateFailedException
	 *             if errors occurs by updating the element.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Aircraft update(Aircraft aircraft) throws UpdateFailedException;

	/**
	 * deletes by the aircraft represented object from database.
	 * 
	 * @param aircraft
	 *            object to delete.
	 * 
	 * @return updated aircraft object after delete.
	 * @throws DeleteFailedException
	 *             if errors occurs by delete the element.
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Aircraft delete(Aircraft aircraft) throws DeleteFailedException;

}
