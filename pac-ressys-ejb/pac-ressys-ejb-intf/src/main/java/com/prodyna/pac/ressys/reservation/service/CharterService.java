/**
 * 
 */
package com.prodyna.pac.ressys.reservation.service;

import javax.ws.rs.Path;

import com.prodyna.pac.ressys.basis.service.BasisRessysService;
import com.prodyna.pac.ressys.reservation.model.Charter;

/**
 * Interface for Charter service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Path("/charter")
public interface CharterService extends BasisRessysService<Charter> {

}
