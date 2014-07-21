/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.model;

import javax.persistence.Entity;

import com.prodyna.pac.ressys.basis.model.BasisRessysEntity;

/**
 * The entity representing aircrafts.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
public class Aircraft extends BasisRessysEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8223854882376376187L;

	private AircraftType aircraftType;	
	private String aircraftName;
	
	/**
	 * 
	 */
	public Aircraft() {
		super();
	}

	/**
	 * @param aircraftType
	 * @param aircraftName
	 */
	public Aircraft(AircraftType aircraftType, String aircraftName) {
		super();
		this.aircraftType = aircraftType;
		this.aircraftName = aircraftName;
	}

	/**
	 * @param id
	 * @param aircraftType
	 * @param aircraftName
	 */
	public Aircraft(long id, AircraftType aircraftType, String aircraftName) {
		super(id);
		this.aircraftType = aircraftType;
		this.aircraftName = aircraftName;
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

	/**
	 * @return the aircraftName
	 */
	public String getAircraftName() {
		return aircraftName;
	}

	/**
	 * @param aircraftName the aircraftName to set
	 */
	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Aircraft [id=" + getId() + ", aircraftType=" + aircraftType
				+ ", aircraftName=" + aircraftName + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aircraftName == null) ? 0 : aircraftName.hashCode());
		result = prime * result
				+ ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
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
		Aircraft other = (Aircraft) obj;
		if (aircraftName == null) {
			if (other.aircraftName != null)
				return false;
		} else if (!aircraftName.equals(other.aircraftName))
			return false;
		if (aircraftType == null) {
			if (other.aircraftType != null)
				return false;
		} else if (!aircraftType.equals(other.aircraftType))
			return false;
		if (getId() != other.getId())
			return false;
		return true;
	}
	
}
