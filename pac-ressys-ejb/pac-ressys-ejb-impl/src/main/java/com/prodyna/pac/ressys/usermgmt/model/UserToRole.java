/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * this entity represents the assigment of roles to a user
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@IdClass(UserToRoleKey.class)
@Entity
@Table(name="user_to_role", uniqueConstraints=@UniqueConstraint(columnNames={"user_id", "role_id"}))
public class UserToRole {

	@Id
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name="role_id", referencedColumnName="id")
	private Role role;
	
	public UserToRole(){
		
	}
	
	public UserToRole(User user, Role role){
		this.user = user;
		this.role = role;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserToRole [user=" + user + ", role=" + role + "]";
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
		UserToRole other = (UserToRole) obj;
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
