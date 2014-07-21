/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.model;

import javax.persistence.Entity;

import com.prodyna.pac.ressys.basis.model.BasisRessysEntity;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
public class Role extends BasisRessysEntity {

	private String role;
	
	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param role
	 */
	public Role(String role) {
		super();
		this.role = role;
	}

	/**
	 * @param id
	 * @param role
	 */
	public Role(long id, String role) {
		super(id);
		this.role = role;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [id=" + getId() + ", role=" + role + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Role other = (Role) obj;
		if (getId() != other.getId())
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
	
	
	
}
