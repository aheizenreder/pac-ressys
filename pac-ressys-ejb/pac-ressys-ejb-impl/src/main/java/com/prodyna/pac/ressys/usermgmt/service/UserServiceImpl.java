/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.service;

import java.security.MessageDigest;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.xml.bind.DatatypeConverter;

import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;
import com.prodyna.pac.ressys.usermgmt.exception.MultipleResultsForAUserException;
import com.prodyna.pac.ressys.usermgmt.exception.RoleAlreadyAssignedException;
import com.prodyna.pac.ressys.usermgmt.exception.RoleNotAssignedException;
import com.prodyna.pac.ressys.usermgmt.exception.UserNotFoundException;
import com.prodyna.pac.ressys.usermgmt.model.Role;
import com.prodyna.pac.ressys.usermgmt.model.User;
import com.prodyna.pac.ressys.usermgmt.model.UserToRole;
import com.prodyna.pac.ressys.usermgmt.model.UserToRoleKey;

/**
 * Implementation of the user service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Stateless
public class UserServiceImpl extends BasisRessysServiceImpl<User> implements
		UserService {

	@Inject
	private Logger log;

	@Inject
	private MessageDigest messageDigest;

	@Inject
	private RoleService roleService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.UserService#create(com.prodyna
	 * .pac.ressys.usermgmt.model.User)
	 */
	@Override
	public User create(User user) {
		log.info("persist user ...");
		// encrypt user password before persist user.
		user = encryptPassword(user);

		User persisted = createEntity(user);
		return persisted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.basis.service.BasisRessysService#get(java.lang
	 * .Long)
	 */
	@Override
	public User get(Long id) {
		log.info("get user by id: " + id + "...");
		User us = getEntity(User.class, id);

		// because while persisting the user the password was encrypted
		// set passwordEncrypted to true
		if (us != null) {
			us.setPasswordEncrypted(true);
		}

		return us;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.ressys.basis.service.BasisRessysService#getAll()
	 */
	@Override
	public List<User> getAll() {
		log.info("get all users ...");
		List<User> resultList = getAllEntities(User.SELECT_ALL_USER);
		log.info("found " + resultList.size() + " users.");
		// set password encrypted flag to true for all found entities
		for (User user : resultList) {
			if (user != null) {
				user.setPasswordEncrypted(true);
			} else {
				// this situation should not exist, but if an exception will be
				// thrown
				throw new NullPointerException(
						"null value in user result list!");
			}
		}
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.UserService#update(com.prodyna
	 * .pac.ressys.usermgmt.model.User)
	 */
	@Override
	public User update(User user) {
		log.info("update user ..");

		user = encryptPassword(user);

		User updated = updateEntity(user);
		return updated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.basis.service.BasisRessysService#delete(java.lang
	 * .Object)
	 */
	@Override
	public User delete(User entity) {
		log.info("Delete user with id " + entity.getId());
		User us = deleteEntity(User.class, entity.getId());
		log.info("User with id " + us.getId() + " was deleted.");

		return us;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.UserService#findUser(java.lang
	 * .String, java.lang.String, boolean)
	 */
	@Override
	public User findUser(String loginName, String password,
			boolean passwordEncrypted) throws UserNotFoundException,
			MultipleResultsForAUserException {
		log.info("START findUser() ...");
		String passwordToUse = password;
		// encrypt password if it is not.
		if (!passwordEncrypted) {
			passwordToUse = encryptPassword(password);
		}
		// create named Query.
		TypedQuery<User> findUserQuery = getEntityManager().createNamedQuery(
				User.FIND_USER, User.class);
		findUserQuery.setParameter(User.FIND_USER_PARAMETER_NAME_LOGIN_NAME,
				loginName);
		findUserQuery.setParameter(User.FIND_USER_PARAMETER_NAME_PASSWORD,
				passwordToUse);

		List<User> resultList = findUserQuery.getResultList();
		if (resultList.isEmpty()) {
			// throw exception
			throw new UserNotFoundException(loginName);
		}

		if (resultList.size() > 1) {
			// more than one user was found
			throw new MultipleResultsForAUserException(loginName);
		}
		log.info("return only one expected ...");

		User resultUser = resultList.get(0);
		resultUser.setPasswordEncrypted(true);
		log.info("END findUser().");
		return resultUser;
	}

	public User findByLoginName(String loginName) throws UserNotFoundException,
			MultipleResultsForAUserException {
		log.info("START findUser() ...");
		// create named Query.
		TypedQuery<User> findUserQuery = getEntityManager().createNamedQuery(
				User.FIND_USER_BY_LOGIN_NAME, User.class);
		findUserQuery.setParameter(
				User.FIND_USER_BY_LOGIN_NAME_PARAMETER_NAME_LOGIN_NAME,
				loginName);

		List<User> resultList = findUserQuery.getResultList();
		if (resultList.isEmpty()) {
			// throw exception
			throw new UserNotFoundException(loginName);
		}

		if (resultList.size() > 1) {
			// more than one user was found
			throw new MultipleResultsForAUserException(loginName);
		}
		log.info("return only one expected ...");

		User resultUser = resultList.get(0);
		log.info("END findUser().");
		return resultUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.UserService#addRole(com.prodyna
	 * .pac.ressys.usermgmt.model.User,
	 * com.prodyna.pac.ressys.usermgmt.model.Role)
	 */
	@Override
	public boolean addRole(Long userId, Long roleId)
			throws RoleAlreadyAssignedException {
		log.info("START addRole() ...");

		// get user from db
		User user = get(userId);
		// get role from db
		Role role = roleService.get(roleId);

		// check if the assignment already exists
		List<Role> rolesList = getRoles(user.getId());
		if (!rolesList.isEmpty() && rolesList.contains(role)) {
			// the requested Role is already assigned to the user
			// throw a exception.
			log.severe("The requested assosiation already exists!");
			throw new RoleAlreadyAssignedException(user, role);
		}

		// assign role to the user
		UserToRole utr = new UserToRole(user, role);
		getEntityManager().persist(utr);
		getEntityManager().flush();

		log.info("END addRole().");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.UserService#removeRole(java.lang
	 * .Long, java.lang.Long)
	 */
	@Override
	public boolean removeRole(Long userId, Long roleId)
			throws RoleNotAssignedException {
		log.info("START removeRole() ...");

		// get user from db
		User user = get(userId);
		// get role from db
		Role role = roleService.get(roleId);

		// check if the assignment already exists
		List<Role> rolesList = getRoles(user.getId());
		if (!rolesList.isEmpty() && !rolesList.contains(role)) {
			// the requested Role is not assigned to the user
			// throw a exception.
			log.severe("The role to remove is not assigned to the user!");
			throw new RoleNotAssignedException(user, role);
		}

		UserToRoleKey utrKey = new UserToRoleKey(user.getId(), role.getId());
		UserToRole utr = getEntityManager().find(UserToRole.class, utrKey);
		getEntityManager().remove(utr);
		getEntityManager().flush();

		log.info("END removeRole().");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.UserService#getRoles(com.prodyna
	 * .pac.ressys.usermgmt.model.User)
	 */
	@Override
	public List<Role> getRoles(Long userId) {
		log.info("START getRoles() ...");
		TypedQuery<Role> findRolesForUser = getEntityManager()
				.createNamedQuery(UserToRole.FIND_ROLES_FOR_USER, Role.class);
		findRolesForUser.setParameter(
				UserToRole.FIND_ROLES_FOR_USER_PARAMETER_NAME_USER_ID, userId);
		List<Role> resultList = findRolesForUser.getResultList();

		log.info("END getRoles().");
		return resultList;
	}

	/**
	 * This method check the encryption of the user password and if the password
	 * is not encrypted, then encrypt a plane text password (String) with a hash
	 * algorithm provided by injected MessageDigest instance. To check which
	 * hash algorithm is used please check Resources class.
	 * 
	 * @param user
	 *            User instance to check the password encryption.
	 * @return User instance with encrypted and updated password.
	 */
	private User encryptPassword(User user) {

		String resultPassword;

		if (!user.isPasswordEncrypted()) {
			resultPassword = encryptPassword(user.getPassword());
			user.setPassword(resultPassword);
			user.setPasswordEncrypted(true);
		}

		return user;
	}

	/**
	 * this method encrypts a plane String with a hash algorithm provided by
	 * injected MessageDigest.
	 * 
	 * @param planePassword
	 *            a String to encrypt.
	 * @return hash encrypted value for planePassword.
	 */
	private String encryptPassword(String planePassword) {
		String resultPassword;

		byte[] digestPassword = messageDigest.digest(planePassword.getBytes());
		resultPassword = DatatypeConverter.printHexBinary(digestPassword)
				.toLowerCase();

		return resultPassword;
	}

}
