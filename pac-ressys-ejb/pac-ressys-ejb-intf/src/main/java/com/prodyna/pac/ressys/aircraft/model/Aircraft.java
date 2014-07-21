/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.prodyna.pac.ressys.basis.model.BasisRessysEntity;

/**
 * The entity representing aircrafts.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = Aircraft.SELECT_ALL_AIRCRAFT, query = "SELECT a FROM Aircraft a") })
@Table(name = "aircraft", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Aircraft extends BasisRessysEntity {

	public static final String SELECT_ALL_AIRCRAFT = "selectAllAircraft";
	
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
	@NotNull
	@ManyToOne
	@JoinColumn(name="aircraft_type_id", referencedColumnName="id")
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
	@NotNull
	@Size(min=3, max=50)
	@Column(name="aircraft_name")
	public String getAircraftName() {
		return aircraftName;
	}

	/**
	 * 
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
