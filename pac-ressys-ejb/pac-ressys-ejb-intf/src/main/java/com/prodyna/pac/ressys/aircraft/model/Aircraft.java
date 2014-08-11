/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The entity representing aircrafts.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = Aircraft.SELECT_ALL_AIRCRAFT, query = "SELECT a FROM Aircraft a") })
@Table(name = "aircraft", uniqueConstraints = @UniqueConstraint(columnNames = "aircraft_name"))
public class Aircraft implements Serializable {

	public static final String SELECT_ALL_AIRCRAFT = "selectAllAircraft";

	/**
	 * Generated value for serialization.
	 */
	private static final long serialVersionUID = -8223854882376376187L;

	/**
	 * Key field for aircraft.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "aircraft_type_id", referencedColumnName = "id")
	private AircraftType aircraftType;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "aircraft_name")
	private String aircraftName;

	/**
	 * 
	 */
	public Aircraft() {

	}

	/**
	 * @param aircraftType
	 * @param aircraftName
	 */
	public Aircraft(AircraftType aircraftType, String aircraftName) {
		this.aircraftType = aircraftType;
		this.aircraftName = aircraftName;
	}

	/**
	 * @param id
	 * @param aircraftType
	 * @param aircraftName
	 */
	public Aircraft(long id, AircraftType aircraftType, String aircraftName) {
		this.id = id;
		this.aircraftType = aircraftType;
		this.aircraftName = aircraftName;
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

	/**
	 * @return the aircraftName
	 */
	public String getAircraftName() {
		return aircraftName;
	}

	/**
	 * 
	 * @param aircraftName
	 *            the aircraftName to set
	 */
	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Aircraft [id=" + id + ", aircraftType=" + aircraftType
				+ ", aircraftName=" + aircraftName + "]";
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
				+ ((aircraftName == null) ? 0 : aircraftName.hashCode());
		result = prime * result
				+ ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
