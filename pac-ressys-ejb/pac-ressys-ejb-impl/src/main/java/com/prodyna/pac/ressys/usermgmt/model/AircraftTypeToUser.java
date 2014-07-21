/**
 * 
 */
package com.prodyna.pac.ressys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
public class AircraftTypeToUser {

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "aircraft_type_id", referencedColumnName = "id")
	private AircraftType aircraftType;
	
	/**
	 * 
	 */
	public AircraftTypeToUser() {
		super();
	}
	
	/**
	 * @param user
	 * @param aircraftType
	 */
	public AircraftTypeToUser(User user, AircraftType aircraftType) {
		super();
		this.user = user;
		this.aircraftType = aircraftType;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the aircraftType
	 */
	public AircraftType getAircraftType() {
		return aircraftType;
	}

	/**
	 * @param aircraftType the aircraftType to set
	 */
	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AircraftTypeToUser [user=" + user + ", aircraftType="
				+ aircraftType + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		AircraftTypeToUser other = (AircraftTypeToUser) obj;
		if (aircraftType == null) {
			if (other.aircraftType != null)
				return false;
		} else if (!aircraftType.equals(other.aircraftType))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	 
}
