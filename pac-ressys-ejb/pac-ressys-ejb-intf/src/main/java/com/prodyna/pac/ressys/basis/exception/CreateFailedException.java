/**
 * 
 */
package com.prodyna.pac.ressys.basis.exception;

/**
 * Exception for failed creation of an element.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class CreateFailedException extends RessysException {

	/**
	 * generated id for serialization.
	 */
	private static final long serialVersionUID = 3393966420874893852L;

	/**
	 * default constructor without parameters.
	 */
	public CreateFailedException() {
		super();
	}

	/**
	 * constructor with setting message and cause for the exception.
	 * 
	 * @param message
	 *            a String as a message for the exception.
	 * @param cause
	 *            a Throwable instance for the cause of the exception
	 */
	public CreateFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * constructor with setting a message for the exception.
	 * 
	 * @param message
	 *            a String as a message for the exception.
	 */
	public CreateFailedException(String message) {
		super(message);
	}

	/**
	 * constructor with setting a cause for the exception.
	 * 
	 * @param cause
	 *            a Throwable as cause for the exception.
	 */
	public CreateFailedException(Throwable cause) {
		super(cause);
	}

}
