/**
 * 
 */
package com.prodyna.pac.ressys.basis.service;

import java.util.List;

import com.prodyna.pac.ressys.basis.model.BasisRessysEntity;

/**
 * This interface represents basis interface for all ressys services. It defines
 * the CRUD operations.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public interface BasisRessysService<T extends BasisRessysEntity> {

	public T create(T entity);

	public T get(Long id);

	public List<T> getAll();

	public T update(T entity);

	public T delete(T entity);
	
}
