/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.service;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.aircraft.service.AircraftService;
import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class UserServiceImpl extends BasisRessysServiceImpl<User>
implements UserService {
	
	@Inject
	private Logger log;

	/* (non-Javadoc)
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.UserService#create(com.prodyna
	 * .pac.ressys.usermgmt.model.User)
	 */
	@Override
	public User create(User user) {
		log.info("persist user ...");
		User persisted = createEntity(user);
		return persisted;
	}

	/* (non-Javadoc)
	 * @see com.prodyna.pac.ressys.basis.service.BasisRessysService#get(java.lang.Long)
	 */
	@Override
	public User get(Long id) {
		log.info("get user by id: " + id + "...");
		User us = getEntity(User.class, id);
		return us;
	}

	/* (non-Javadoc)
	 * @see com.prodyna.pac.ressys.basis.service.BasisRessysService#getAll()
	 */
	@Override
	public List<User> getAll() {
		log.info("get all users ...");
		List<User> resultList = getAllEntities(User.SELECT_ALL_USER);
		log.info("found " + resultList.size() + " users.");
		return resultList;
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see com.prodyna.pac.ressys.basis.service.BasisRessysService#delete(java.lang.Object)
	 */
	@Override
	public User delete(User entity) {
		log.info("Delete user with id " + entity.getId());
		User us = deleteEntity(User.class, entity.getId());
		log.info("User with id " + us.getId() + " was deleted.");

		return us;
	}

}
