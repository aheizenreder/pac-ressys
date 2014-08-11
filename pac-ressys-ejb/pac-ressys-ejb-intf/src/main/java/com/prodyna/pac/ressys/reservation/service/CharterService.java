/**
 * 
 */
package com.prodyna.pac.ressys.reservation.service;

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
import com.prodyna.pac.ressys.basis.security.AdminUserAccess;
import com.prodyna.pac.ressys.basis.security.AllAccess;
import com.prodyna.pac.ressys.basis.security.Secured;
import com.prodyna.pac.ressys.reservation.exception.CharterCancelException;
import com.prodyna.pac.ressys.reservation.exception.CharterLendException;
import com.prodyna.pac.ressys.reservation.exception.CharterReturnException;
import com.prodyna.pac.ressys.reservation.model.Charter;

/**
 * Interface for Charter service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Secured
@Path("/charter")
public interface CharterService {

	/**
	 * persists the charter in the database.
	 * 
	 * @param charter
	 *            the charter object to persists in the database.
	 * @return the with key information enriched charter object.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Charter create(Charter charter);

	/**
	 * reads the charter identified by id from the database.
	 * 
	 * @param id
	 *            Id of the charter, which is to read.
	 * 
	 * @return from database read charter object.
	 */
	@GET
	@Path("/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public Charter get(@PathParam("id") Long id);

	/**
	 * reads all charter from database.
	 * 
	 * @return a List of entities.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public List<Charter> getAll();

	/**
	 * persists changes on the charter object in the database.
	 * 
	 * @param charter
	 *            the charter object with updated properties, which are to
	 *            persist.
	 * 
	 * @return the updated charter object.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Charter update(Charter charter);

	/**
	 * deletes by the charter represented object from database.
	 * 
	 * @param charter
	 *            object to delete.
	 * 
	 * @return updated charter object after delete.
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public Charter delete(Charter charter);

	/**
	 * cancel a charter. This method checks the current state of charter
	 * identified by id and set the new state to cancel.
	 * 
	 * @param id
	 *            Id of the charter which is to cancel.
	 * @return updated version of the canceled Charter.
	 * @throws CharterCancelException
	 *             by wrong status in charter by cancellation.
	 */
	@GET
	@Path("/{id:[0-9]+/cancel")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminUserAccess
	public Charter cancel(@PathParam("id") Long id)
			throws CharterCancelException;

	/**
	 * set a charter in state "lent".
	 * 
	 * @param id
	 *            Id of the charter which is to set to status "lent".
	 * @return updated version of charter.
	 * @throws CharterLendException
	 *             if the carter is in a wrong state for lend.
	 */
	@GET
	@Path("/{id:[0-9]+/lend")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminUserAccess
	public Charter lend(@PathParam("id") Long id) throws CharterLendException;

	/**
	 * set a charter in state "returned".
	 * 
	 * @param id
	 *            of the charter which is to return.
	 * 
	 * @return updated version of charter.
	 * @throws CharterReturnException
	 *             if charter in has wrong status for return.
	 */
	@GET
	@Path("/{id:[0-9]+/return")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminUserAccess
	public Charter returnCharter(@PathParam("id") Long id)
			throws CharterReturnException;

	/**
	 * set all charter in status 'lent' and which lent date is already expired
	 * in status 'returned'.
	 * 
	 * @return number of returned charter.
	 */
	@GET
	@Path("/returnAllOutstandingLends")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public int returnAllOutstandingLends();
}
