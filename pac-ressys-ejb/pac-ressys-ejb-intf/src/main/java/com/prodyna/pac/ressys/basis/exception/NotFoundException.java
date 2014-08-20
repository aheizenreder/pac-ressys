/**
 * 
 */
package com.prodyna.pac.ressys.basis.exception;

/**
 * Exception for the situations where an element was not found.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class NotFoundException extends RessysException {

	/**
	 * generated id for serialization.
	 */
	private static final long serialVersionUID = -2539966241330666767L;

	/**
	 * requested id of the element.
	 */
	private Long id;

	/**
	 * constructor without parameters.
	 */
	public NotFoundException() {
		super();
	}

	/**
	 * constructor with requested id as parameter.
	 */
	public NotFoundException(Long id) {
		super();
		this.setId(id);
	}

	/**
	 * constructor with requested id, message and cause.
	 * 
	 * @param id
	 *            requested id.
	 * @param message
	 *            a String as description of the exception.
	 * @param cause
	 *            a Throwable as the cause of the exception.
	 */
	public NotFoundException(Long id, String message, Throwable cause) {
		super(message, cause);
		this.id = id;
	}

	/**
	 * constructor with id an message.
	 * 
	 * @param id
	 *            requested id.
	 * @param message
	 *            a String as a message for the exception.
	 */
	public NotFoundException(Long id, String message) {
		super(message);
		this.id = id;
	}

	/**
	 * constructor with id and cause for exception.
	 * 
	 * @param id
	 *            requested id.
	 * @param cause
	 *            a Throwable a cause for the exception.
	 */
	public NotFoundException(Long id, Throwable cause) {
		super(cause);
		this.id = id;
	}

	/**
	 * gets id.
	 * 
	 * @return the id of the exception.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * set a value for the id in exception.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
