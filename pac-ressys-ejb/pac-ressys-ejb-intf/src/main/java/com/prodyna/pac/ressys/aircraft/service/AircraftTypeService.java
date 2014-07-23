/**
 * 
 */
package com.prodyna.pac.ressys.aircraft.service;

import javax.ws.rs.Path;

import com.prodyna.pac.ressys.aircraft.model.AircraftType;
import com.prodyna.pac.ressys.basis.service.BasisRessysService;

/**
 * Interface for operations on AircraftType.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Path("/aircraft_type")
public interface AircraftTypeService extends BasisRessysService<AircraftType> {

}
