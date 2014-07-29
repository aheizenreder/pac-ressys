/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.exception;

/**
 * Exception for the situation that no user can be found by service methods.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class UserNotFoundException extends Exception {

	/**
	 * generated id for serialization
	 */
	private static final long serialVersionUID = 5708938756029455394L;

	/**
	 * default message for this exception type.
	 */
	private static final String USER_NOT_FOUND_MESSAGE = "User not found! Please check login and password!";

	/**
	 * login name of the user
	 */
	private String loginName;

	/**
	 * default constructor without parameters. This overloaded constructor sets
	 * a default message.
	 */
	public UserNotFoundException() {
		super(USER_NOT_FOUND_MESSAGE);
	}

	/**
	 * default consturctor without parameters. This overloaded constructor sets
	 * a default message.
	 * 
	 * @param loginName
	 *            TODO
	 */
	public UserNotFoundException(String loginName) {
		super(USER_NOT_FOUND_MESSAGE);
		this.loginName = loginName;
	}

	/**
	 * constructor for full initialization.
	 * 
	 * @param loginName
	 *            TODO
	 * @param message
	 *            a String as message to set.
	 * @param cause
	 *            the Throwable instance as cause for this exception.
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserNotFoundException(String loginName, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.loginName = loginName;
	}

	/**
	 * constructor with message and cause initialization.
	 * 
	 * @param loginName
	 *            login name of the missing user.
	 * @param message
	 *            message to set to the Exception.
	 * @param cause
	 *            a Throwable instance as cause for this exception.
	 */
	public UserNotFoundException(String loginName, String message,
			Throwable cause) {
		super(message, cause);
		this.loginName = loginName;
	}

	/**
	 * constructor with message.
	 * 
	 * @param message
	 *            message to set to the exception.
	 */
	public UserNotFoundException(String loginName, String message) {
		super(message);
		this.loginName = loginName;
	}

	/**
	 * constructor with cause only initialization. This constructor sets the
	 * default message.
	 * 
	 * @param loginName
	 *            login name for the missing user.
	 * @param cause
	 *            a Throwable instance as cause for this Exception.
	 */
	public UserNotFoundException(String loginName, Throwable cause) {
		super(USER_NOT_FOUND_MESSAGE, cause);
		this.loginName = loginName;
	}

	/**
	 * gets login name for the failed user look up.
	 * 
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * sets a user login name for the failed user look up.
	 * 
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
