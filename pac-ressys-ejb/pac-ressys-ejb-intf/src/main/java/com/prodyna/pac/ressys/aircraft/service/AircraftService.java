/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.service;

import javax.ws.rs.Path;

import com.prodyna.pac.ressys.aircraft.model.Aircraft;
import com.prodyna.pac.ressys.basis.service.BasisRessysService;

/**
 * Interface for service on Aircraft entity.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Path("/aircraft")
public interface AircraftService extends BasisRessysService<Aircraft> {

}
