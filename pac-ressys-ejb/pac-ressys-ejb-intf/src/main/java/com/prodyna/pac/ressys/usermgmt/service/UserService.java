/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.service;

import javax.ws.rs.Path;

import com.prodyna.pac.ressys.basis.service.BasisRessysService;
import com.prodyna.pac.ressys.usermgmt.model.User;

/**
 * Interface for User service.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Path("/user")
public interface UserService extends BasisRessysService<User> {

}
