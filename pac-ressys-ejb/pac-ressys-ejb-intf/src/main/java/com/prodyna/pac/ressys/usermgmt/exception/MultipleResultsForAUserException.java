/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.exception;

/**
 * Exception for multiple results for a login name and password pair.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class MultipleResultsForAUserException extends Exception {

	/**
	 * generated id for serialization.
	 */
	private static final long serialVersionUID = 5658802058417882299L;

	/**
	 * default message.
	 */
	private static final String MULTIPLE_RESULTS_FOR_A_USER_MESSAGE = "More that one user was found by login name and password!";

	/**
	 * login name for user with multiple results.
	 */
	private String loginName;

	/**
	 * constructor without parameters. A default message will be set to this
	 * instance.
	 */
	public MultipleResultsForAUserException() {
		super(MULTIPLE_RESULTS_FOR_A_USER_MESSAGE);
	}

	/**
	 * constructor with login name and default message.
	 * 
	 * @param loginName
	 *            users login name.
	 */
	public MultipleResultsForAUserException(String loginName) {
		super(MULTIPLE_RESULTS_FOR_A_USER_MESSAGE);
		this.loginName = loginName;
	}

	/**
	 * constructor with initialization of login name and message.
	 * 
	 * @param loginName
	 *            users login name
	 * @param message
	 *            String to use as exeption message.
	 */
	public MultipleResultsForAUserException(String loginName, String message) {
		super(message);
		this.loginName = loginName;
	}

	/**
	 * constructor with initialization of login name and Throwable instance for
	 * cause. The message for this exception will be set to default message.
	 * 
	 * @param loginName
	 *            users login name.
	 * @param cause
	 *            a Throwable instance for the cause.
	 */
	public MultipleResultsForAUserException(String loginName, Throwable cause) {
		super(cause);
		this.loginName = loginName;
	}

	/**
	 * constructor with initialization of login name, message and cause.
	 * 
	 * @param loginName
	 *            users login name.
	 * @param message
	 *            a String for a specific exception message.
	 * @param cause
	 *            a Throwable instance for the exception cause.
	 */
	public MultipleResultsForAUserException(String loginName, String message,
			Throwable cause) {
		super(message, cause);
		this.loginName = loginName;
	}

	/**
	 * constructor for the full specific initialization.
	 * 
	 * @param loginName
	 *            users login name.
	 * @param message
	 *            a String for the specific message.
	 * @param cause
	 *            a Throwable instance for the cause.
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MultipleResultsForAUserException(String loginName, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.loginName = loginName;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
