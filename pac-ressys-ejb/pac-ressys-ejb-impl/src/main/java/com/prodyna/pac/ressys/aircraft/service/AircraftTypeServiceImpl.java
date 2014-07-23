/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;

/**
 * Implementation of AircraftTypeService.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Stateless
public class AircraftTypeServiceImpl extends
		BasisRessysServiceImpl<AircraftType> implements AircraftTypeService {

	@Inject
	private Logger log;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.aircraft.service.AircraftTypeService#create(com
	 * .prodyna.pac.ressys.aircraft.model.AircraftType)
	 */
	@Override
	public AircraftType create(AircraftType aircraftType) {
		log.info("create aircraft type ...");
		return createEntity(aircraftType);
	}

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
		AircraftType act = getEntity(AircraftType.class, id);
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
		List<AircraftType> resultList = getAllEntities(AircraftType.SELECT_ALL_AIRCRAFT_TYPES);
		log.info("get " + resultList.size() + " aircraft types.");
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.aircraft.service.AircraftTypeService#update(com
	 * .prodyna.pac.ressys.aircraft.model.AircraftType)
	 */
	@Override
	public AircraftType update(AircraftType aircraftType) {
		log.info("update aircraft type ...");
		AircraftType updated = updateEntity(aircraftType);
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
	public AircraftType delete(AircraftType entity) {
		log.info("Delete aircraft type with id " + entity.getId());
		AircraftType act = deleteEntity(AircraftType.class, entity.getId());
		log.info("Aircraft type with id " + entity.getId() + " was deleted.");

		return act;
	}

}
