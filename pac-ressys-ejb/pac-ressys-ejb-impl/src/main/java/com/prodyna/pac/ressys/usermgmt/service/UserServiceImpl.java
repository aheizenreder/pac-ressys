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
import com.prodyna.pac.ressys.usermgmt.exception.UserNotFoundException;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
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
		if (!user.isPasswordEncrypted()) {
			String encPassword = encryptPassword(user.getPassword());
			user.setPassword(encPassword);
			user.setPasswordEncrypted(true);
		}
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
		us.setPasswordEncrypted(true);

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

	/**
	 * This method encrypt a plane text password (String) with a hash algorithm
	 * provided by injected MessageDigest instance. To check which hash
	 * algorithm is used please check Ressources class.
	 * 
	 * @param planePassword
	 *            a String to encrypt.
	 * @return with a hash algorithm calculated hash value from planePassword.
	 */
	private String encryptPassword(String planePassword) {

		byte[] digestPassword = messageDigest.digest(planePassword.getBytes());
		String encryptedPassword = DatatypeConverter.printHexBinary(
				digestPassword).toLowerCase();

		return encryptedPassword;
	}

}
