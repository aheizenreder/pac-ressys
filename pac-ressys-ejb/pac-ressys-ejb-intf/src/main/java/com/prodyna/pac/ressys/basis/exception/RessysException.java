/**
 * 
 */
package com.prodyna.pac.ressys.basis.exception;

import javax.ejb.ApplicationException;

/**
 * Basis exception for all exceptions in pac ressys components.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@ApplicationException(inherited = true)
public class RessysException extends Exception {

	/**
	 * generated id for serialization
	 */
	private static final long serialVersionUID = 8907577332231156110L;

	/**
	 * default constructor without parameters.
	 */
	public RessysException() {
		super();
	}

	/**
	 * constructor for setting message and cause.
	 * 
	 * @param message
	 *            message for the exception.
	 * @param cause
	 *            a Throwable for the exception.
	 */
	public RessysException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * constructor to set message to the exception.
	 * 
	 * @param message
	 *            a String representing the exception message.
	 */
	public RessysException(String message) {
		super(message);
	}

	/**
	 * constructor to set cause to the exception.
	 * 
	 * @param cause
	 *            Throwable as the cause for the message.
	 */
	public RessysException(Throwable cause) {
		super(cause);
	}

}
