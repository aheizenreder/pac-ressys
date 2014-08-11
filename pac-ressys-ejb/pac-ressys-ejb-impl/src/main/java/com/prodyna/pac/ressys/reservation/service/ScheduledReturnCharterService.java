/**
 * 
 */
package com.prodyna.pac.ressys.reservation.service;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Service to set charter in status lent automatic to the status returned, if
 * the end time is expired.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Singleton
@Startup
public class ScheduledReturnCharterService {

	@Inject
	private Logger log;

	@Inject
	private CharterService charterService;

	private Date lastTimeout;

	/**
	 * this method runs every one minute
	 */
	@Schedule(minute = "*/1", hour = "*", persistent = false)
	public void returnExpiredCharter() {
		log.info("START returnExpiredCharter() ...");

		this.setLastTimeout(new Date());

		int numReturnedCharter = charterService.returnAllOutstandingLends();
		log.info(numReturnedCharter + " returned");

		log.info("END returnExpiredCharter().");
	}

	/**
	 * @return the lastTimeout
	 */
	public Date getLastTimeout() {
		return lastTimeout;
	}

	/**
	 * @param lastTimeout
	 *            the lastTimeout to set
	 */
	public void setLastTimeout(Date lastTimeout) {
		this.lastTimeout = lastTimeout;
	}
}
