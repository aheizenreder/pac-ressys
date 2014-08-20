/**
 * 
 */
package com.prodyna.pac.ressys.basis.exception;

/**
 * Exception for failed update on an element.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class UpdateFailedException extends RessysException {

	/**
	 * generated id for serialization.
	 */
	private static final long serialVersionUID = -2977797969419214001L;

	/**
	 * default constructor without parameters.
	 */
	public UpdateFailedException() {
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
	public UpdateFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * constructor with setting a message for the exception.
	 * 
	 * @param message
	 *            a String as a message for the exception.
	 */
	public UpdateFailedException(String message) {
		super(message);
	}

	/**
	 * constructor with setting a cause for the exception.
	 * 
	 * @param cause
	 *            a Throwable as cause for the exception.
	 */
	public UpdateFailedException(Throwable cause) {
		super(cause);
	}


}
