/**
 * 
 */
package com.prodyna.pac.ressys.basis.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.annotation.security.RolesAllowed;

/**
 * Annotation for access by user with administrator role only.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Inherited
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Documented
@RolesAllowed("Admin")
public @interface AdminAccessOnly {

}
