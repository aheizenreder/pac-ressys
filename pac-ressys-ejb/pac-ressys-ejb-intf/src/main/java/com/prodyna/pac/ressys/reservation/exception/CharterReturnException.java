/**
 * 
 */
package com.prodyna.pac.ressys.reservation.exception;

import com.prodyna.pac.ressys.reservation.model.Charter;

/**
 * Exception if a charter can not be
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class CharterReturnException extends Exception {

	/**
	 * default message for the exception.
	 */
	private static final String DEFAULT_MESSAGE = "Can't return charter! Please check charter status!";

	/**
	 * generated id for serialization.
	 */
	private static final long serialVersionUID = 3238496144606070638L;

	/**
	 * Charter which return was not possible.
	 */
	private Charter charter;

	/**
	 * constructor without parameters.
	 */
	public CharterReturnException() {
		super(DEFAULT_MESSAGE);
	}

	/**
	 * constructor with charter for initialization.
	 * 
	 * @param charter
	 *            charter from return.
	 */
	public CharterReturnException(Charter charter) {
		super(DEFAULT_MESSAGE);
		this.charter = charter;
	}

	/**
	 * constructor with charter und message initialization.
	 * 
	 * @param charter
	 *            charter instance from return.
	 * @param message
	 *            message for the exception.
	 */
	public CharterReturnException(Charter charter, String message) {
		super(message);
		this.charter = charter;
	}

	/**
	 * constructor with charter and cause initialization. This constructor sets
	 * the message for this exception to default message.
	 * 
	 * @param charter
	 *            charter instance return.
	 * @param cause
	 *            the cause for this exception.
	 */
	public CharterReturnException(Charter charter, Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
		this.charter = charter;
	}

	/**
	 * constructor with charter, message and cause for initialization.
	 * 
	 * @param charter
	 *            charter from return.
	 * @param message
	 *            message for the exception.
	 * @param cause
	 *            cause for the exception
	 */
	public CharterReturnException(Charter charter, String message,
			Throwable cause) {
		super(message, cause);
		this.charter = charter;
	}

	/**
	 * constructor with full initialization.
	 * 
	 * @param charter
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CharterReturnException(Charter charter, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.charter = charter;
	}

	/**
	 * get the charter instance from Exception.
	 * 
	 * @return the charter
	 */
	public Charter getCharter() {
		return charter;
	}

	/**
	 * set Charter instance to exception.
	 * 
	 * @param charter
	 *            the charter to set
	 */
	public void setCharter(Charter charter) {
		this.charter = charter;
	}

}
