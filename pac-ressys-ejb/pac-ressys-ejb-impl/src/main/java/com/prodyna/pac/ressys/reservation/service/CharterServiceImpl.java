/**
 * 
 */
package com.prodyna.pac.ressys.reservation.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;
import com.prodyna.pac.ressys.reservation.model.Charter;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Stateless
public class CharterServiceImpl extends BasisRessysServiceImpl<Charter> implements
		CharterService {
	
	@Inject
	private Logger log;

	/* (non-Javadoc)
	 * @see com.prodyna.pac.ressys.reservation.service.CharterService#create(com.prodyna.pac.ressys.reservation.model.Charter)
	 */
	@Override
	public Charter create(Charter charter) {
		log.info("persist charter ...");
		Charter persisted = createEntity(charter);
		return persisted;
	}

	/* (non-Javadoc)
	 * @see com.prodyna.pac.ressys.reservation.service.CharterService#get(java.lang.Long)
	 */
	@Override
	public Charter get(Long id) {
		log.info("get charter by id: " + id + "...");
		Charter ch = getEntity(Charter.class, id);
		return ch;
	}

	/* (non-Javadoc)
	 * @see com.prodyna.pac.ressys.reservation.service.CharterService#getAll()
	 */
	@Override
	public List<Charter> getAll() {
		log.info("get all charters ...");
		List<Charter> resultList = getAllEntities(Charter.SELECT_ALL_CHARTER);
		log.info("found " + resultList.size() + " charters.");
		return resultList;
	}

	/* (non-Javadoc)
	 * @see com.prodyna.pac.ressys.reservation.service.CharterService#update(com.prodyna.pac.ressys.reservation.model.Charter)
	 */
	@Override
	public Charter update(Charter charter) {
		log.info("update charter ..");
		Charter updated = updateEntity(charter);
		return updated;
	}

	/* (non-Javadoc)
	 * @see com.prodyna.pac.ressys.reservation.service.CharterService#delete(com.prodyna.pac.ressys.reservation.model.Charter)
	 */
	@Override
	public Charter delete(Charter charter) {
		log.info("Delete charter with id " + charter.getId());
		Charter ch = deleteEntity(Charter.class, charter.getId());
		log.info("Charter with id " + ch.getId() + " was deleted.");

		return ch;
	}

}
