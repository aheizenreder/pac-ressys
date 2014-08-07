/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.exception;

import com.prodyna.pac.ressys.usermgmt.model.Role;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * Exception to inform, that a role is already assigned to a user.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class RoleAlreadyAssignedException extends Exception {

	/**
	 * generated id for serialization.
	 */
	private static final long serialVersionUID = 2531117014996974187L;

	/**
	 * Default message for this exception.
	 */
	private static final String DEFAULT_MESSAGE = "User has already requested role!";

	/**
	 * user for assignment.
	 */
	private User user;

	/**
	 * already assigned role.
	 */
	private Role role;

	/**
	 * default constructor without parameters. This constructor sets only
	 * default message.
	 */
	public RoleAlreadyAssignedException() {
		super(DEFAULT_MESSAGE);
	}

	/**
	 * constructor with initialization with user and role.
	 * 
	 * @param user
	 *            user to use in exception.
	 * @param role
	 *            role to use in exception.
	 */
	public RoleAlreadyAssignedException(User user, Role role) {
		super(DEFAULT_MESSAGE);
		this.user = user;
		this.role = role;
	}

	/**
	 * constructor with initialization of user, role and message
	 * 
	 * @param user
	 *            user to set in exception.
	 * @param role
	 *            role to set in exception.
	 * @param message
	 *            the message for exception.
	 */
	public RoleAlreadyAssignedException(User user, Role role, String message) {
		super(message);
		this.user = user;
		this.role = role;
	}

	/**
	 * constructor with initialization with user, role and cause. This
	 * constructor sets a default message in addition to the cause.
	 * 
	 * @param user
	 *            user to set in exception.
	 * @param role
	 *            role to set in exception.
	 * @param cause
	 *            a Throwable for the cause of this exception.
	 */
	public RoleAlreadyAssignedException(User user, Role role, Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
		this.user = user;
		this.role = role;
	}

	/**
	 * constructor with initialization of user, role, message and cause.
	 * 
	 * @param user
	 *            user to set in exception.
	 * @param role
	 *            role to set in exception.
	 * @param message
	 *            message to set to exception.
	 * @param cause
	 *            a Throwable to set to exception.
	 */
	public RoleAlreadyAssignedException(User user, Role role, String message,
			Throwable cause) {
		super(message, cause);
		this.user = user;
		this.role = role;
	}

	/**
	 * constructor with complete set of parameters.
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RoleAlreadyAssignedException(User user, Role role, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.user = user;
		this.role = role;
	}

	/**
	 * gets the user associated with this exception.
	 * 
	 * @return the user from exception.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * sets user to the exception.
	 * 
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * gets the role associated with this exception.
	 * 
	 * @return the role from exception.
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * sets role to exception.
	 * 
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}
