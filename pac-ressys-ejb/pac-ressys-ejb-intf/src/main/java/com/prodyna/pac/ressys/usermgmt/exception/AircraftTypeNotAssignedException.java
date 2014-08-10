/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.exception;

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.usermgmt.model.Role;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class AircraftTypeNotAssignedException extends Exception{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2744775028304489115L;

	/**
	 * Default message for this exception.
	 */
	private static final String DEFAULT_MESSAGE = "Aircraft type is not assigned to the user!";

	/**
	 * user for removing a aircraft type.
	 */
	private User user;

	/**
	 * requested aircraftType.
	 */
	private AircraftType aircraftType;

	/**
	 * default constructor without parameters. This constructor sets only
	 * default message.
	 */
	public AircraftTypeNotAssignedException() {
		super(DEFAULT_MESSAGE);
	}

	/**
	 * constructor with initialization with user and aircraft type.
	 * 
	 * @param user
	 *            user to use in exception.
	 * @param aircraftType
	 *            aircraft type to use in exception.
	 */
	public AircraftTypeNotAssignedException(User user, AircraftType aircraftType) {
		super(DEFAULT_MESSAGE);
		this.user = user;
		this.aircraftType = aircraftType;
	}

	/**
	 * constructor with initialization of user, aircraft type and message
	 * 
	 * @param user
	 *            user to set in exception.
	 * @param aircraftType
	 *            aircraft type to set in exception.
	 * @param message
	 *            the message for exception.
	 */
	public AircraftTypeNotAssignedException(User user, AircraftType aircraftType, String message) {
		super(message);
		this.user = user;
		this.aircraftType = aircraftType;
	}

	/**
	 * constructor with initialization with user, role and cause. This
	 * constructor sets a default message in addition to the cause.
	 * 
	 * @param user
	 *            user to set in exception.
	 * @param aircraftType
	 *            aircraft type to set in exception.
	 * @param cause
	 *            a Throwable for the cause of this exception.
	 */
	public AircraftTypeNotAssignedException(User user, AircraftType aircraftType, Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
		this.user = user;
		this.aircraftType = aircraftType;
	}

	/**
	 * constructor with initialization of user, aircraft type, message and cause.
	 * 
	 * @param user
	 *            user to set in exception.
	 * @param aircraftType
	 *            aircraft type to set in exception.
	 * @param message
	 *            message to set to exception.
	 * @param cause
	 *            a Throwable to set to exception.
	 */
	public AircraftTypeNotAssignedException(User user, AircraftType aircraftType, String message,
			Throwable cause) {
		super(message, cause);
		this.user = user;
		this.aircraftType = aircraftType;
	}

	/**
	 * constructor with complete set of parameters.
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AircraftTypeNotAssignedException(User user, AircraftType aircraftType, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.user = user;
		this.aircraftType = aircraftType;
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
	 * gets the aircraft type associated with this exception.
	 * 
	 * @return the aircraft type from exception.
	 */
	public AircraftType getAircraftTypee() {
		return aircraftType;
	}

	/**
	 * sets Aircraft type to exception.
	 * 
	 * @param aircraftType
	 *            the aircraftType to set
	 */
	public void setAircraftTypee(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}
}