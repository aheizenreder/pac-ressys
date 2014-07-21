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
 * Annotations for common representation of access to class or methods by user
 * with normal user role or administrator role.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Inherited
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Documented
@RolesAllowed({"Admin", "User"})
public @interface AdminUserAccess {

}
