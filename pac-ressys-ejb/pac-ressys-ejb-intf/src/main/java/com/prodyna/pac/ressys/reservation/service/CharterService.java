/**
 * 
 */
package com.prodyna.pac.ressys.reservation.service;

import javax.ws.rs.Path;

import com.prodyna.pac.ressys.basis.security.Secured;
import com.prodyna.pac.ressys.monitoring.logging.Logged;
import com.prodyna.pac.ressys.monitoring.performance.Monitored;

/**
 * Interface for Charter service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Logged
@Monitored
@Secured
@Path("/charter")
public interface CharterService {

}
