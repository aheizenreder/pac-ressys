/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;

/**
 * Implementation of AircraftTypeService.
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Stateless
@Path("aircraft_type")
public class AircraftTypeServiceImpl extends
		BasisRessysServiceImpl<AircraftType> implements AircraftTypeService {

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
	public AircraftType get(Long id) {
		log.info("get aircraft type by id: " + id + " ...");
		AircraftType act = get(AircraftType.class, id);
		return act;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.ressys.basis.service.BasisRessysService#getAll()
	 */
	@Override
	public List<AircraftType> getAll() {
		log.info("get all aircraft types ...");
		List<AircraftType> resultList = getAll(AircraftType.SELECT_ALL_AIRCRAFT_TYPES);
		log.info("get " + resultList.size() + " aircraft types.");
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
	public AircraftType delete(AircraftType entity) {
		log.info("Delete aircraft type with id " + entity.getId());
		AircraftType act = delete(AircraftType.class, entity.getId());
		log.info("Aircraft type with id " + entity.getId() + " was deleted.");

		return act;
	}

}
