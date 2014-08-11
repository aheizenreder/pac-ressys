/**
 * 
 */
package com.prodyna.pac.ressys.reservation.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.prodyna.pac.ressys.basis.service.BasisRessysServiceImpl;
import com.prodyna.pac.ressys.monitoring.logging.Logged;
import com.prodyna.pac.ressys.monitoring.performance.Monitored;
import com.prodyna.pac.ressys.reservation.exception.CharterCancelException;
import com.prodyna.pac.ressys.reservation.exception.CharterLendException;
import com.prodyna.pac.ressys.reservation.exception.CharterReturnException;
import com.prodyna.pac.ressys.reservation.model.Charter;
import com.prodyna.pac.ressys.reservation.model.CharterState;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Logged
@Monitored
@Stateless
public class CharterServiceImpl extends BasisRessysServiceImpl<Charter>
		implements CharterService {

	@Inject
	private Logger log;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.reservation.service.CharterService#create(com.
	 * prodyna.pac.ressys.reservation.model.Charter)
	 */
	@Override
	public Charter create(Charter charter) {
		log.info("persist charter ...");
		charter.setCharterState(CharterState.RESERVED);
		Charter persisted = createEntity(charter);
		return persisted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.reservation.service.CharterService#get(java.lang
	 * .Long)
	 */
	@Override
	public Charter get(Long id) {
		log.info("get charter by id: " + id + "...");
		Charter ch = getEntity(Charter.class, id);
		return ch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.ressys.reservation.service.CharterService#getAll()
	 */
	@Override
	public List<Charter> getAll() {
		log.info("get all charters ...");
		List<Charter> resultList = getAllEntities(Charter.SELECT_ALL_CHARTER);
		log.info("found " + resultList.size() + " charters.");
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.reservation.service.CharterService#update(com.
	 * prodyna.pac.ressys.reservation.model.Charter)
	 */
	@Override
	public Charter update(Charter charter) {
		log.info("update charter ..");
		Charter updated = updateEntity(charter);
		return updated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.reservation.service.CharterService#delete(com.
	 * prodyna.pac.ressys.reservation.model.Charter)
	 */
	@Override
	public Charter delete(Charter charter) {
		log.info("Delete charter with id " + charter.getId());
		Charter ch = deleteEntity(Charter.class, charter.getId());
		log.info("Charter with id " + ch.getId() + " was deleted.");

		return ch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.reservation.service.CharterService#cancel(java
	 * .lang.Long)
	 */
	@Override
	public Charter cancel(Long id) throws CharterCancelException {
		Charter charter = get(id);
		if (!CharterState.RESERVED.equals(charter.getCharterState())) {
			log.info("charter can not be canceled more!");
			throw new CharterCancelException(charter);
		}
		// set status to cancelled.
		charter.setCharterState(CharterState.CANCELLED);
		charter = update(charter);

		return charter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.reservation.service.CharterService#lend(java.lang
	 * .Long)
	 */
	@Override
	public Charter lend(Long id) throws CharterLendException {

		Charter charter = get(id);
		if (!CharterState.RESERVED.equals(charter.getCharterState())) {
			log.log(Level.SEVERE, "charter can not be lent more!");
			throw new CharterLendException(charter);
		}
		// set status to lend
		charter.setCharterState(CharterState.LENT);
		charter = update(charter);

		return charter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.reservation.service.CharterService#returnCharter
	 * (java.lang.Long)
	 */
	@Override
	public Charter returnCharter(Long id) throws CharterReturnException {

		Charter charter = get(id);
		if (!CharterState.LENT.equals(charter.getCharterState())) {
			log.log(Level.SEVERE, "Charter can not be returned!");
			throw new CharterReturnException(charter);
		}
		// set status to lend
		charter.setCharterState(CharterState.RETURNED);
		charter = update(charter);
		return charter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.ressys.reservation.service.CharterService#
	 * returnAllOutstandingLends()
	 */
	@Override
	public int returnAllOutstandingLends() {
		log.info("START returnAllOutstandingLends() ...");
		Query returnLentCharter = getEntityManager().createNamedQuery(
				Charter.RETURN_LENT_CHARTER);
		Calendar endDateCal = Calendar.getInstance();
		Date endDate = endDateCal.getTime();
		returnLentCharter.setParameter(
				Charter.RETURN_LENT_CHARTER_PARAMETER_NAME_END_DATE, endDate);
		int numUpdatedCharter = returnLentCharter.executeUpdate();
		log.info(numUpdatedCharter + " charter set to returned.");
		log.info("END returnAllOutstandingLends().");
		return numUpdatedCharter;
	}

}
