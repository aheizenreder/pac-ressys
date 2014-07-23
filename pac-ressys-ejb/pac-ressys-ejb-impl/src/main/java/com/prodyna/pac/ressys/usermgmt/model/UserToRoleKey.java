/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.model;

import java.io.Serializable;

/**
 * combined key for user to role assignment.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class UserToRoleKey implements Serializable {

	/**
	 * generated uid for serialization.
	 */
	private static final long serialVersionUID = -4610135457571078170L;

	/**
	 * id of the user as the part of the key.
	 */
	private Long user;

	/**
	 * id of the role as the part of the key
	 */
	private Long role;

	/**
	 * default constructor.
	 */
	public UserToRoleKey() {

	}

	public UserToRoleKey(Long user, Long role) {
		this.user = user;
		this.role = role;
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
	public void setUser(Long user) {
		this.user = user;
	}

	/**
	 * @return the role
	 */
	public Long getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Long role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserToRoleKey [user=" + user + ", role=" + role + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		UserToRoleKey other = (UserToRoleKey) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
