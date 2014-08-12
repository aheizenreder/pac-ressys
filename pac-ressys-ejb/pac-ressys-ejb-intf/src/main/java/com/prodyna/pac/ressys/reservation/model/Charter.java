/**
 * 
 */
package com.prodyna.pac.ressys.reservation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * Entity class for Charter.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Charter.SELECT_ALL_CHARTER, query = "SELECT a FROM Charter a"),
		@NamedQuery(name = Charter.RETURN_LENT_CHARTER, query = "UPDATE Charter c SET c.charterState = com.prodyna.pac.ressys.reservation.model.CharterState.RETURNED WHERE (c.charterState = com.prodyna.pac.ressys.reservation.model.CharterState.LENT OR c.charterState = com.prodyna.pac.ressys.reservation.model.CharterState.RESERVED) AND c.endDate < :end_date") })
@Table(name = "charter", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Charter implements Serializable {

	public static final String SELECT_ALL_CHARTER = "selectAllCharter";

	public static final String RETURN_LENT_CHARTER = "returnLentCharter";

	public static final String RETURN_LENT_CHARTER_PARAMETER_NAME_END_DATE = "end_date";

	/**
	 * generated uid for serialization.
	 */
	private static final long serialVersionUID = 4153825894732339204L;

	/**
	 * Key field for charter entities.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Start date may not be null!")
	@Future(message = "Start date must be in the future!")
	@Column(name = "start_date")
	private Date startDate;

	@NotNull(message = "End date may not be null!")
	@Future(message = "End date must be in the future!")
	@Column(name = "end_date")
	private Date endDate;

	@NotNull(message = "Pilot may not be null!")
	@ManyToOne
	@JoinColumn(name = "pilot_id", referencedColumnName = "id")
	private User pilot;

	@NotNull(message = "Aircraft may not be null!")
	@ManyToOne
	@JoinColumn(name = "aircraft_id", referencedColumnName = "id")
	private Aircraft aircraft;

	@NotNull(message = "No state for charter set!")
	@Enumerated(EnumType.STRING)
	@Column(name = "charter_state")
	private CharterState charterState;

	/**
	 * 
	 */
	public Charter() {
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
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pilot = pilot;
		this.aircraft = aircraft;
		this.charterState = charterState;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the startDate
	 */
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
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
	 * @param endDate
	 *            the endDate to set
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
	 * @param pilot
	 *            the pilot to set
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
	 * @param aircraft
	 *            the aircraft to set
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
	 * @param charterState
	 *            the charterState to set
	 */
	public void setCharterState(CharterState charterState) {
		this.charterState = charterState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Charter [id=" + id + ", startDate=" + startDate + ", endDate="
				+ endDate + ", pilot=" + pilot + ", aircraft=" + aircraft
				+ ", charterState=" + charterState + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (charterState != other.charterState)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
