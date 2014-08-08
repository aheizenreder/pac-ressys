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
import com.prodyna.pac.ressys.usermgmt.model.Role;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Secured
@Path("/role")
public interface RoleService {

	/**
	 * persists the role in the database.
	 * 
	 * @param role
	 *            the role object to persists in the database.
	 * @return the with key information enriched role  object.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Role create(Role role);

	/**
	 * reads the role identified by id from the database.
	 * 
	 * @param id
	 *            Id of the role, which is to read.
	 * 
	 * @return from database read role object.
	 */
	@GET
	@Path("/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public Role get(@PathParam("id") Long id);

	/**
	 * reads all role from database.
	 * 
	 * @return a List of entities.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public List<Role> getAll();

	/**
	 * persists changes on the role object in the database.
	 * 
	 * @param role
	 *            the role object with updated properties, which are to
	 *            persist.
	 * 
	 * @return the updated role object.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Role update(Role role);

	/**
	 * deletes by the role represented object from database.
	 * 
	 * @param role
	 *            object to delete.
	 * 
	 * @return updated role object after delete.
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Role delete(Role role);
	
}
