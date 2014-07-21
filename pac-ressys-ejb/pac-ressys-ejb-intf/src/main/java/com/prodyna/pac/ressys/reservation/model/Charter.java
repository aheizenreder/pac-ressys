/**
 * 
 */
package com.prodyna.pac.ressys.reservation.model;

import java.util.Date;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.basis.model.BasisRessysEntity;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * Entity class for Charter.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class Charter extends BasisRessysEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4153825894732339204L;

	private Date startDate;
	private Date endDate;
	private User pilot;
	private Aircraft aircraft;
	private CharterState charterState;
	
	/**
	 * 
	 */
	public Charter() {
		super();
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @param pilot
	 * @param aircraft
	 * @param charterState
	 */
	public Charter(Date startDate, Date endDate, User pilot, Aircraft aircraft,
			CharterState charterState) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.pilot = pilot;
		this.aircraft = aircraft;
		this.charterState = charterState;
	}

	/**
	 * @param id
	 * @param startDate
	 * @param endDate
	 * @param pilot
	 * @param aircraft
	 * @param charterState
	 */
	public Charter(long id, Date startDate, Date endDate, User pilot,
			Aircraft aircraft, CharterState charterState) {
		super(id);
		this.startDate = startDate;
		this.endDate = endDate;
		this.pilot = pilot;
		this.aircraft = aircraft;
		this.charterState = charterState;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the pilot
	 */
	public User getPilot() {
		return pilot;
	}

	/**
	 * @param pilot the pilot to set
	 */
	public void setPilot(User pilot) {
		this.pilot = pilot;
	}

	/**
	 * @return the aircraft
	 */
	public Aircraft getAircraft() {
		return aircraft;
	}

	/**
	 * @param aircraft the aircraft to set
	 */
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	/**
	 * @return the charterState
	 */
	public CharterState getCharterState() {
		return charterState;
	}

	/**
	 * @param charterState the charterState to set
	 */
	public void setCharterState(CharterState charterState) {
		this.charterState = charterState;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Charter [id=" + getId() + ", startDate=" + startDate + ", endDate="
				+ endDate + ", pilot=" + pilot + ", aircraft=" + aircraft
				+ ", charterState=" + charterState + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aircraft == null) ? 0 : aircraft.hashCode());
		result = prime * result
				+ ((charterState == null) ? 0 : charterState.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Charter other = (Charter) obj;
		if (aircraft == null) {
			if (other.aircraft != null)
				return false;
		} else if (!aircraft.equals(other.aircraft))
			return false;
		if (charterState == null) {
			if (other.charterState != null)
				return false;
		} else if (!charterState.equals(other.charterState))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (getId() != other.getId())
			return false;
		if (pilot == null) {
			if (other.pilot != null)
				return false;
		} else if (!pilot.equals(other.pilot))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
}
