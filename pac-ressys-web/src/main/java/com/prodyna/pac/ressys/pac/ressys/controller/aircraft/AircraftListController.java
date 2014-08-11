/**
 * 
 */
package com.prodyna.pac.ressys.pac.ressys.controller.aircraft;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.aircraft.service.AircraftService;
import com.prodyna.pac.ressys.pac.ressys.controller.login.LoginController;
import com.prodyna.pac.ressys.pac.ressys.util.PacRessysRestClientProducer;

/**
 * ManagedBean for managing aircraft list representation in ui.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Model
public class AircraftListController {

	@Inject
	private LoginController loginController;

	public List<Aircraft> getAircraftList() {
		List<Aircraft> resultList;
		AircraftService aircraftService = PacRessysRestClientProducer
				.getPacRessysService(loginController.getLoginName(),
						loginController.getPassword(), AircraftService.class);
		resultList = aircraftService.getAll();
		return resultList;
	}
}
