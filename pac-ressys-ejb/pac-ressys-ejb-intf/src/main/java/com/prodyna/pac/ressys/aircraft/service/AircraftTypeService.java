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

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.basis.security.AdminAccessOnly;
import com.prodyna.pac.ressys.basis.security.AllAccess;
import com.prodyna.pac.ressys.basis.security.Secured;

/**
 * Interface for operations on AircraftType.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Secured
@Path("/aircraft_type")
public interface AircraftTypeService {

	/**
	 * persists the aircraft type in the database.
	 * 
	 * @param aircraftType
	 *            the aircraft type object to persists in the database.
	 * @return the with key information enriched aircraft type  object.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public AircraftType create(AircraftType aircraftType);

	/**
	 * reads the aircraft type identified by id from the database.
	 * 
	 * @param id
	 *            Id of the aircraft type, which is to read.
	 * 
	 * @return from database read aircraft type object.
	 */
	@GET
	@Path("/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public AircraftType get(@PathParam("id") Long id);

	/**
	 * reads all aircraft type from database.
	 * 
	 * @return a List of entities.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public List<AircraftType> getAll();

	/**
	 * persists changes on the aircraft object in the database.
	 * 
	 * @param aircraftType
	 *            the aircraft type object with updated properties, which are to
	 *            persist.
	 * 
	 * @return the updated aircraft type object.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public AircraftType update(AircraftType aircraftType);

	/**
	 * deletes by the aircraft type represented object from database.
	 * 
	 * @param aircraftType
	 *            object to delete.
	 * 
	 * @return updated aircraft type object after delete.
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public AircraftType delete(AircraftType aircraftType);

}
