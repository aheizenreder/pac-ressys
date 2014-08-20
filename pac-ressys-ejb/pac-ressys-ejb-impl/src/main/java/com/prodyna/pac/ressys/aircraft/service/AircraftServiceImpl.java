/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.basis.exception.CreateFailedException;
import com.prodyna.pac.ressys.basis.exception.DeleteFailedException;
import com.prodyna.pac.ressys.basis.exception.NotFoundException;
import com.prodyna.pac.ressys.basis.exception.UpdateFailedException;
import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;
import com.prodyna.pac.ressys.monitoring.logging.Logged;
import com.prodyna.pac.ressys.monitoring.performance.Monitored;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Logged
@Monitored
@Stateless
public class AircraftServiceImpl extends BasisRessysServiceImpl<Aircraft>
		implements AircraftService {

	@Inject
	private Logger log;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.aircraft.service.AircraftService#create(com.prodyna
	 * .pac.ressys.aircraft.model.Aircraft)
	 */
	@Override
	public Aircraft create(Aircraft aircraft) throws CreateFailedException {
		log.info("persist aircraft ...");
		Aircraft persisted = null;
		try {
			persisted = createEntity(aircraft);
		} catch (Exception e) {
			String message = "Cannot create aircraft entry: " + e.getMessage();
			log.log(Level.SEVERE, message);
			throw new CreateFailedException(message);
		}
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
	public Aircraft get(Long id) throws NotFoundException {
		log.info("get aircraft by id: " + id + "...");
		Aircraft ac = getEntity(Aircraft.class, id);
		if (ac == null) {
			log.info("no aircraft was found for the id: " + id);
			throw new NotFoundException(id, "No aircraft found for id " + id
					+ "!");
		}
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
		List<Aircraft> resultList = getAllEntities(Aircraft.SELECT_ALL_AIRCRAFT);
		log.info("found " + resultList.size() + " aircrafts.");
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.aircraft.service.AircraftService#update(com.prodyna
	 * .pac.ressys.aircraft.model.Aircraft)
	 */
	@Override
	public Aircraft update(Aircraft aircraft) throws UpdateFailedException {
		log.info("update aircraft ..");
		Aircraft updated = null;
		try {
			updated = updateEntity(aircraft);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Cannot update aircraft: " + e.getMessage());
			throw new UpdateFailedException("Cannot update aircraft("
					+ aircraft.getId() + "): " + e.getMessage());
		}
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
	public Aircraft delete(Aircraft entity) throws DeleteFailedException {
		log.info("Delete aircraft with id " + entity.getId());
		Aircraft ac = null;
		try {
			ac = deleteEntity(Aircraft.class, entity.getId());
		} catch (Exception e) {
			log.log(Level.SEVERE, "Cannot delete aircraft(" + entity.getId()
					+ "): " + e.getMessage());
			throw new DeleteFailedException("Cannot delete aircraft("
					+ entity.getId() + "): " + e.getMessage());
		}
		
		log.info("Aircraft with id " + ac.getId() + " was deleted.");

		return ac;
	}

}
