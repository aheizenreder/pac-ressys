/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * this entity is used for the relation between users and aircraft types. This
 * relation represents the aircraft types which an user can fly.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@IdClass(AircraftTypeToUserKey.class)
@Entity
@NamedQueries({
		@NamedQuery(name = AircraftTypeToUser.FIND_AIRCRAFT_TYPE_FOR_USER, query = "SELECT attu.aircraftType FROM AircraftTypeToUser attu WHERE attu.user.id = :user_id"),
		@NamedQuery(name = AircraftTypeToUser.FIND_USER_FOR_AIRCRAFT_TYPE, query = "SELECT attu.user FROM AircraftTypeToUser attu WHERE attu.aircraftType.id = :aircraft_type_id") })
@Table(name = "aircraft_type_to_user", uniqueConstraints = @UniqueConstraint(columnNames = {
		"aircraft_type_id", "user_id" }))
public class AircraftTypeToUser {

	/**
	 * Name of the find aircraft types for a user query.
	 */
	public static final String FIND_AIRCRAFT_TYPE_FOR_USER = "findAircraftTypeForUser";
	/**
	 * Parameter name for user id in find aircraft types for a user query.
	 */
	public static final String FIND_AIRCRAFT_TYPE_FOR_USER_PARAMETER_NAME_USER_ID = "user_id";

	/**
	 * Name of the find user for a aircraft type query.
	 */
	public static final String FIND_USER_FOR_AIRCRAFT_TYPE = "findUserForAircraftType";
	/**
	 * Parameter name for aircraft type id in find user for a aircraft type
	 * query.
	 */
	public static final String FIND_USER_FOR_AIRCRAFT_TYPE_PARAMETER_NAME_AIRCRAFT_TYPE_ID = "aircraft_type_id";

	@Id
	@ManyToOne
	@JoinColumn(name = "aircraft_type_id", referencedColumnName = "id")
	private AircraftType aircraftType;

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	/**
	 * 
	 */
	public AircraftTypeToUser() {

	}

	/**
	 * @param user
	 * @param aircraftType
	 */
	public AircraftTypeToUser(User user, AircraftType aircraftType) {
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
	 * @param user
	 *            the user to set
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
	 * @param aircraftType
	 *            the aircraftType to set
	 */
	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AircraftTypeToUser [user=" + user + ", aircraftType="
				+ aircraftType + "]";
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
