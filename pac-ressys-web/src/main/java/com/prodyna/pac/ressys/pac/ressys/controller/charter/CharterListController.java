/**
 * 
 */
package com.prodyna.pac.ressys.pac.ressys.controller.charter;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.prodyna.pac.ressys.pac.ressys.controller.login.LoginController;
import com.prodyna.pac.ressys.pac.ressys.util.PacRessysRestClientProducer;
import com.prodyna.pac.ressys.reservation.exception.CharterLendException;
import com.prodyna.pac.ressys.reservation.model.Charter;
import com.prodyna.pac.ressys.reservation.service.CharterService;

/**
 * ManagedBean for managing charter list in ui.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Model
public class CharterListController {

	@Inject
	private LoginController loginController;

	@Inject
	private FacesContext facesContext;

	public List<Charter> getCharterList() {
		List<Charter> resultList = null;

		CharterService charterService = getCharterService();

		resultList = charterService.getAll();

		return resultList;
	}

	public void cancelCharter(Long charterId) {
		CharterService charterService = getCharterService();
		try {
			charterService.cancel(charterId);
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Can't cancel your reservation! Please check the reservation first!",
					"");
			facesContext.addMessage(null, m);

		}

	}

	public void lendCharter(Long charterId) {

		CharterService charterService = getCharterService();
		try {
			charterService.lend(charterId);
		} catch (CharterLendException e) {
			FacesMessage m = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Can't lend your reservation! Please check the reservation first!",
					"");
			facesContext.addMessage(null, m);
		}
	}

	private CharterService getCharterService() {
		CharterService charterService = PacRessysRestClientProducer
				.getPacRessysService(loginController.getLoginName(),
						loginController.getPassword(), CharterService.class);

		return charterService;
	}
}
