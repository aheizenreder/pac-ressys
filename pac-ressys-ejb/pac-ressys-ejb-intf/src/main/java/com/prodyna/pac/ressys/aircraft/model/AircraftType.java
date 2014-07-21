/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.model;

import javax.persistence.Entity;

import com.prodyna.pac.ressys.basis.model.BasisRessysEntity;

/**
 * This entity implements types of aircrafts.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
public class AircraftType extends BasisRessysEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2656345636188023807L;

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
		super(id);
		this.typeName = typeName;
	}


	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AircraftType [id=" + getId() + ", typeName=" + typeName + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
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
		AircraftType other = (AircraftType) obj;
		if (getId() != other.getId())
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}
	
}
