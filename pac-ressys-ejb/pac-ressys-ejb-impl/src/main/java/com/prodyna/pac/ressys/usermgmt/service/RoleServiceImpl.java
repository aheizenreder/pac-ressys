/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;
import com.prodyna.pac.ressys.monitoring.logging.Logged;
import com.prodyna.pac.ressys.monitoring.performance.Monitored;
import com.prodyna.pac.ressys.usermgmt.model.Role;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Logged
@Monitored
@Stateless
public class RoleServiceImpl extends BasisRessysServiceImpl<Role> implements
		RoleService {

	@Inject
	private Logger log;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.RoleService#create(com.prodyna
	 * .pac.ressys.usermgmt.model.Role)
	 */
	@Override
	public Role create(Role role) {
		log.info("persist role ...");
		Role persisted = createEntity(role);
		return persisted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.RoleService#get(java.lang.Long)
	 */
	@Override
	public Role get(Long id) {
		log.info("get role by id: " + id + "...");
		Role role = getEntity(Role.class, id);
		return role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.ressys.usermgmt.service.RoleService#getAll()
	 */
	@Override
	public List<Role> getAll() {
		log.info("get all roles ...");
		List<Role> resultList = getAllEntities(Role.SELECT_ALL_ROLE);
		log.info("found " + resultList.size() + " roles.");
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.RoleService#update(com.prodyna
	 * .pac.ressys.usermgmt.model.Role)
	 */
	@Override
	public Role update(Role role) {
		log.info("update role ..");
		Role updated = updateEntity(role);
		return updated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.usermgmt.service.RoleService#delete(com.prodyna
	 * .pac.ressys.usermgmt.model.Role)
	 */
	@Override
	public Role delete(Role role) {
		log.info("Delete role with id " + role.getId());
		Role r = deleteEntity(Role.class, role.getId());
		log.info("Role with id " + r.getId() + " was deleted.");

		return r;
	}

}
