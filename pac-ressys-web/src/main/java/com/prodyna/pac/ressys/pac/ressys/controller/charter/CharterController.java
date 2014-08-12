/**
 * 
 */
package com.prodyna.pac.ressys.pac.ressys.controller.charter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.aircraft.service.AircraftService;
import com.prodyna.pac.ressys.pac.ressys.controller.login.LoginController;
import com.prodyna.pac.ressys.pac.ressys.util.PacRessysRestClientProducer;
import com.prodyna.pac.ressys.reservation.model.Charter;
import com.prodyna.pac.ressys.reservation.model.CharterState;
import com.prodyna.pac.ressys.reservation.service.CharterService;

/**
 * Managed bean for create reservation view.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Model
public class CharterController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2363112533661877963L;

	// private Logger log = Logger.getLogger(CharterController.class.getName());

	private static final DateFormat DF = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm");

	@Inject
	private FacesContext facesContext;

	@Inject
	private Validator validator;

	@Inject
	private LoginController loginController;

	/**
	 * Charter service.
	 */
	private CharterService charterService;

	/**
	 * aircraft service to get aircrafts by id.
	 */
	private AircraftService aircraftService;

	/**
	 * Charter.
	 */
	private Charter charter;

	/**
	 * Aircraft
	 */
	private Aircraft aircraft;

	/**
	 * id of the aircraft. Is provided by ui.
	 */
	private Long aircraftId;

	/**
	 * id of the charter. Is provided by ui in case of a edit.
	 */
	private Long charterId;

	/**
	 * Init. the charter.
	 */
	@PostConstruct
	public void initNew() {
		charter = new Charter();
	}

	/**
	 * Save the charter.
	 */
	public String saveCharter() {
		Set<ConstraintViolation<Charter>> validate = validator
				.validate(charter);

		charterService = getCharterService();

		if (validate.isEmpty()) {
			try {
				if (charter.getId() == null) {
					charterService.create(charter);
				} else {
					charterService.update(charter);
				}
				return "saved";
			} catch (Exception e) {
				throw new ValidationException("Unable to save charter", e);
			}
		} else {
			for (ConstraintViolation<?> constraintViolation : validate) {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						constraintViolation.getMessage(), "");
				facesContext.addMessage(null, m);
			}
		}
		return "notSaved";
	}

	private CharterService getCharterService() {
		if (charterService == null) {
			charterService = PacRessysRestClientProducer.getPacRessysService(
					loginController.getLoginName(),
					loginController.getPassword(), CharterService.class);
		}
		return charterService;
	}

	private AircraftService getAircraftService() {
		if (aircraftService == null) {
			aircraftService = PacRessysRestClientProducer.getPacRessysService(
					loginController.getLoginName(),
					loginController.getPassword(), AircraftService.class);
		}
		return aircraftService;
	}

	/**
	 * @return the aircraftId
	 */
	public Long getAircraftId() {
		return aircraftId;
	}

	/**
	 * @param aircraftId
	 *            the aircraftId to set
	 */
	public void setAircraftId(Long aircraftId) {
		if (aircraftId != null) {
			this.aircraftId = aircraftId;

			// initialize Aircraft for reservation.
			AircraftService aircraftService = getAircraftService();
			aircraft = aircraftService.get(aircraftId);
			charter.setPilot(loginController.getLoginUser());
			charter.setCharterState(CharterState.RESERVED);
			charter.setAircraft(aircraft);
		}
	}

	/**
	 * @return the charterId
	 */
	public Long getCharterId() {
		return charterId;
	}

	/**
	 * @param charterId
	 *            the charterId to set
	 */
	public void setCharterId(Long charterId) {
		if (charterId != null) {
			this.charterId = charterId;
			// get Charter by id
			charter = getCharterService().get(charterId);
		}
	}

	/**
	 * @return the charter
	 */
	public Charter getCharter() {
		return charter;
	}

	/**
	 * @return the aircraft
	 */
	public Aircraft getAircraft() {
		return aircraft;
	}

	public String getCharterStartDate() {
		String result = "";
		if (charter != null && charter.getStartDate() != null) {
			result = DF.format(charter.getStartDate());
		}

		return result;
	}

	public void setCharterStartDate(String startDate) {
		if (startDate != null && startDate.trim().length() > 0) {

			try {
				Date startDateDate = DF.parse(startDate);
				charter.setStartDate(startDateDate);
			} catch (ParseException e) {
				FacesMessage m = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Please check start date! Expected format is 'dd.MM.YYYY HH:mm'",
						"");
				facesContext.addMessage(null, m);
			}
		}
	}

	public String getCharterEndDate() {
		String result = "";
		if (charter != null && charter.getEndDate() != null) {
			result = DF.format(charter.getEndDate());
		}

		return result;
	}

	public void setCharterEndDate(String endDate) {
		if (endDate != null && endDate.trim().length() > 0) {

			try {
				Date endDateDate = DF.parse(endDate);
				charter.setEndDate(endDateDate);
			} catch (ParseException e) {
				FacesMessage m = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Please check end date! Expected format is 'dd.MM.YYYY HH:mm'",
						"");
				facesContext.addMessage(null, m);
			}
		}
	}
}
