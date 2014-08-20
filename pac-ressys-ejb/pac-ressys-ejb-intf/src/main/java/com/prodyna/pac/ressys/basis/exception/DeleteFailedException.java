/**
 * 
 */
package com.prodyna.pac.ressys.basis.exception;

/**
 * Exception if an element can not be deleted.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class DeleteFailedException extends RessysException {

	/**
	 * generated id for serialization.
	 */
	private static final long serialVersionUID = -5484976893939275175L;
	/**
	 * default constructor without parameters.
	 */
	public DeleteFailedException() {
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
	public DeleteFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * constructor with setting a message for the exception.
	 * 
	 * @param message
	 *            a String as a message for the exception.
	 */
	public DeleteFailedException(String message) {
		super(message);
	}

	/**
	 * constructor with setting a cause for the exception.
	 * 
	 * @param cause
	 *            a Throwable as cause for the exception.
	 */
	public DeleteFailedException(Throwable cause) {
		super(cause);
	}


}
