/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This entity implements types of aircrafts.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = AircraftType.SELECT_ALL_AIRCRAFT_TYPES, query = "SELECT at FROM AircraftType at") })
@Table(name = "aircraft_type", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class AircraftType implements Serializable {

	public static final String SELECT_ALL_AIRCRAFT_TYPES = "selectAllAircraftTypes";

	/**
	 * generated uid for serialization.
	 */
	private static final long serialVersionUID = -2656345636188023807L;

	/**
	 * Key field for AircraftType.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long id;

	/**
	 * name of the type
	 */
	@NotNull
	@Size(min = 3, max = 100)
	private String typeName;

	/**
	 * 
	 */
	public AircraftType() {
		super();
	}

	/**
	 * @param typeName
	 */
	public AircraftType(String typeName) {
		super();
		this.typeName = typeName;
	}

	/**
	 * @param id
	 * @param typeName
	 */
	public AircraftType(long id, String typeName) {
		this.id = id;
		this.typeName = typeName;
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AircraftType [id=" + id + ", typeName=" + typeName + "]";
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
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
		AircraftType other = (AircraftType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

}
