/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Stateless
public class AircraftServiceImpl extends BasisRessysServiceImpl<Aircraft>
		implements AircraftService {

	@Inject
	private Logger log;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.basis.service.BasisRessysService#get(java.lang
	 * .Long)
	 */
	@Override
	public Aircraft get(Long id) {
		log.info("get aircraft by id: " + id + "...");
		Aircraft ac = get(Aircraft.class, id);
		return ac;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.ressys.basis.service.BasisRessysService#getAll()
	 */
	@Override
	public List<Aircraft> getAll() {
		log.info("get all aircrafts ...");
		List<Aircraft> resultList = getAll(Aircraft.SELECT_ALL_AIRCRAFT);
		log.info("found "+resultList.size()+" aircrafts.");
		return resultList;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.basis.service.BasisRessysService#delete(java.lang
	 * .Object)
	 */
	@Override
	public Aircraft delete(Aircraft entity) {
		log.info("Delete aircraft with id " + entity.getId());
		Aircraft ac = delete(Aircraft.class, entity.getId());
		log.info("Aircraft with id " + ac.getId() + " was deleted.");

		return ac;
	}

}
