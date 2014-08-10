/**
 * 
 */
package com.prodyna.pac.ressys.pac.ressys.controller.charter;

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import com.prodyna.pac.ressys.pac.ressys.util.PacRessysRestClientProducer;
import com.prodyna.pac.ressys.reservation.model.Charter;
import com.prodyna.pac.ressys.reservation.service.CharterService;

/**
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class CharterController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2363112533661877963L;

	@Inject
	private FacesContext facesContext;

	/**
	 * Charter service.
	 */
	private CharterService charterService;

	@Inject
	private Validator validator;

	private Logger log = Logger.getLogger(CharterController.class.getName());

	/**
	 * Charter bean.
	 */
	private Charter charter;

	/**
	 * Init. the charter bean.
	 */
	@PostConstruct
	public void initNew() {
		charter = new Charter();
	}

	/**
	 * Save the charter.
	 */
	public void saveCharter() {
		Set<ConstraintViolation<Charter>> validate = validator
				.validate(charter);
		if (validate.isEmpty()) {
			try {
				if (charter.getId() == 0) {
					charterService.create(charter);
				} else {
					charterService.update(charter);
				}
			} catch (Exception e) {
				throw new ValidationException("Unable to save charter", e);
			}
		} else {
			for (ConstraintViolation<?> constraintViolation : validate) {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						constraintViolation.getMessage(), "");
				facesContext.addMessage(null, m);
			}
			throw new ValidationException("Entity not valid.");
		}
	}

	private CharterService getCharterService() {
		return PacRessysRestClientProducer
				.getPacRessysService(CharterService.class);
	}
}
