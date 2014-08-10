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

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.basis.security.AdminAccessOnly;
import com.prodyna.pac.ressys.basis.security.AllAccess;
import com.prodyna.pac.ressys.basis.security.Secured;
import com.prodyna.pac.ressys.usermgmt.exception.AircraftTypeAlreadyAssignedException;
import com.prodyna.pac.ressys.usermgmt.exception.AircraftTypeNotAssignedException;
import com.prodyna.pac.ressys.usermgmt.exception.MultipleResultsForAUserException;
import com.prodyna.pac.ressys.usermgmt.exception.RoleAlreadyAssignedException;
import com.prodyna.pac.ressys.usermgmt.exception.RoleNotAssignedException;
import com.prodyna.pac.ressys.usermgmt.exception.UserNotFoundException;
import com.prodyna.pac.ressys.usermgmt.model.Role;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * Interface for User service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Secured
@Path("/user")
public interface UserService {
	/**
	 * persists the user in the database.
	 * 
	 * @param user
	 *            the user object to persists in the database.
	 * @return the with key information enriched user object.
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
	 *            the user object with updated properties, which are to persist.
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

	/**
	 * reads the user identified by its login name from the database.
	 * 
	 * @param loginName
	 *            login name of the user, which is to read.
	 * 
	 * @return from database read user object.
	 */
	@GET
	@Path("/findByLoginName/{loginname}")
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public User findByLoginName(@PathParam("loginname") String loginName)
			throws UserNotFoundException, MultipleResultsForAUserException;

	/**
	 * search for a user by its login name and password. If in parameter
	 * password provided value represents the a hash encrypted password then
	 * passwordEncrypted should be set to true, else to false. If
	 * passwordEncrypted is set to false, then the value in password parameter
	 * will be used to calculate a hash value before the database request will
	 * be executed.
	 * 
	 * @param loginName
	 *            String with users login name to look for.
	 * @param password
	 *            password for the user look up. If passwordEncrypted is true,
	 *            then a SHA-256 hash value for password with hex encoding is
	 *            expected.
	 * @param passwordEncrypted
	 *            is true if the value in parameter password is a hash value,
	 *            false if the password is provided in plane form.
	 * @return a User instance if a user could be found in system by login name
	 *         and password, null else.
	 */
	@POST
	@Path("/findUser/{loginName}/{passwordEncrypted}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public User findUser(@PathParam("loginName") String loginName,
			String password,
			@PathParam("passwordEncrypted") boolean passwordEncrypted)
			throws UserNotFoundException, MultipleResultsForAUserException;

	/**
	 * adds a aircraft type to the user.
	 * 
	 * @param userId
	 *            id of the user which have to get the new aircraft type assigned.
	 * @param aircraftTypeId
	 *            id of the aircraft type which is to assign to the user.
	 * @return true if the aircraftType was assigned to the user.
	 * @throws AircraftTypeAlreadyAssignedException
	 *             if the aircraftType already assigned to the user.
	 */
	@GET
	@Path("/{userid:[0-9]+}/addAircraftType/{aircrafttypeid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public boolean addAircraftType(@PathParam("userid") Long userId,
			@PathParam("aircrafttypeid") Long typeId)
			throws AircraftTypeAlreadyAssignedException;

	/**
	 * remove a aircraft type of a the user.
	 * 
	 * @param userId
	 *            id of the user which aircraft type has to be removed.
	 * @param aircraftTypeId
	 *            id of the aircraft type which is to remove from user.
	 * @return true if the aircraft type was removed from user.
	 * @throws AircraftTypeAlreadyAssignedException
	 *             if the aircraft type already assigned to the user.
	 */
	@GET
	@Path("/{userid:[0-9]+}/removeAircraftType/{aircrafttypeid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public boolean removeAircraftType(@PathParam("userid") Long userId,
			@PathParam("aircrafttypeid") Long aircraftTypeId) throws AircraftTypeNotAssignedException;

	/**
	 * reads all aircraft types of a user.
	 * 
	 * @param user
	 *            user to get aircraft type for.
	 * @return a list with all aircraft types for given user.
	 */
	@GET
	@Path("/{userid:[0-9]+}/getuseraircrafttypes")
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public List<AircraftType> getUserAircraftTypes(@PathParam("userid") Long userId);
	
	/**
	 * adds a role to the user.
	 * 
	 * @param userId
	 *            id of the user which have to get the new role assigned.
	 * @param roleId
	 *            id of the role which is to assign to the user.
	 * @return true if the role was assigned to the user.
	 * @throws RoleAlreadyAssignedException
	 *             if the role already assigned to the user.
	 */
	@GET
	@Path("/{userid:[0-9]+}/addRole/{roleid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public boolean addRole(@PathParam("userid") Long userId,
			@PathParam("roleid") Long roleId)
			throws RoleAlreadyAssignedException;

	/**
	 * remove a role of a the user.
	 * 
	 * @param userId
	 *            id of the user which role has to be removed.
	 * @param roleId
	 *            id of the role which is to remove from user.
	 * @return true if the role was removed from user.
	 * @throws RoleAlreadyAssignedException
	 *             if the role already assigned to the user.
	 */
	@GET
	@Path("/{userid:[0-9]+}/removeRole/{roleid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@AdminAccessOnly
	public boolean removeRole(@PathParam("userid") Long userId,
			@PathParam("roleid") Long roleId) throws RoleNotAssignedException;

	/**
	 * reads all roles of a user.
	 * 
	 * @param user
	 *            user to get role for.
	 * @return a list with all roles for given user.
	 */
	@GET
	@Path("/{userid:[0-9]+}/getUserRoles")
	@Produces(MediaType.APPLICATION_JSON)
	@AllAccess
	public List<Role> getRoles(@PathParam("userid") Long userId);
}
