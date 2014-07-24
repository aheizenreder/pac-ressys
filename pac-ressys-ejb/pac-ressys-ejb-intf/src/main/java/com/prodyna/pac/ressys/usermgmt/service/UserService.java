/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.service;

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
import com.prodyna.pac.ressys.monitoring.performance.Monitored;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * Interface for User service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Logged
@Monitored
@Secured
@Path("/user")
public interface UserService{
	/**
	 * persists the user in the database.
	 * 
	 * @param user
	 *            the user object to persists in the database.
	 * @return the with key information enriched user  object.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public User create(User user);

	/**
	 * reads the user identified by id from the database.
	 * 
	 * @param id
	 *            Id of the user, which is to read.
	 * 
	 * @return from database read user object.
	 */
	@GET
	@Path("/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public User get(@PathParam("id") Long id);

	/**
	 * reads all user from database.
	 * 
	 * @return a List of entities.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public List<User> getAll();

	/**
	 * persists changes on the user object in the database.
	 * 
	 * @param user
	 *            the user object with updated properties, which are to
	 *            persist.
	 * 
	 * @return the updated user object.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public User update(User user);

	/**
	 * deletes by the user represented object from database.
	 * 
	 * @param user
	 *            object to delete.
	 * 
	 * @return updated user object after delete.
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public User delete(User user);
}
