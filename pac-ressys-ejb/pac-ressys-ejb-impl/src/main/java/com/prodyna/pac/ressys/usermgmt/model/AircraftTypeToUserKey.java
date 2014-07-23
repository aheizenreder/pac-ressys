/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.model;

import java.io.Serializable;

/**
 * this class represents combined key for aircraftType to user relation.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class AircraftTypeToUserKey implements Serializable {

	/**
	 * generated uid for serialization.
	 */
	private static final long serialVersionUID = -8206762085552680790L;

	/**
	 * id of aircraft type as part of the key.
	 */
	Long aircraftType;

	/**
	 * id of the user as the part of the key.
	 */
	Long user;

	/**
	 * default empty constructor.
	 */
	public AircraftTypeToUserKey() {

	}

	/**
	 * constructor with initialization with parameters.
	 * 
	 * @param aircraftType
	 * @param user
	 */
	public AircraftTypeToUserKey(Long aircraftTypeId, Long userId) {
		super();
		this.aircraftType = aircraftTypeId;
		this.user = userId;
	}

	/**
	 * @return the aircraftType
	 */
	public Long getAircraftType() {
		return aircraftType;
	}

	/**
	 * @param aircraftType
	 *            the aircraftType to set
	 */
	public void setAircraftType(Long aircraftType) {
		this.aircraftType = aircraftType;
	}

	/**
	 * @return the user
	 */
	public Long getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUserId(Long user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AircraftTypeToUserKey [aircraftType=" + aircraftType
				+ ", user=" + user + "]";
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
				+ ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		AircraftTypeToUserKey other = (AircraftTypeToUserKey) obj;
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
