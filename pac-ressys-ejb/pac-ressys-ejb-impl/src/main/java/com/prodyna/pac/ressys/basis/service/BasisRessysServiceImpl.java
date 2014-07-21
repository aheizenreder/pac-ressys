/**
 * 
 */
package com.prodyna.pac.ressys.basis.service;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Basis implementation for ressys services.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public abstract class BasisRessysServiceImpl<T> implements
		BasisRessysService<T> {

	@Inject
	private Logger log;

	@Inject
	EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.basis.service.BasisRessysService#create(java.lang
	 * .Object)
	 */
	public T create(T entity) {
		log.info("Create new  entity in the database ...");
		em.persist(entity);
		log.info("Entity " + entity.toString() + " persisted . ");

		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.basis.service.BasisRessysService#get(java.lang
	 * .Long)
	 */
	protected T get(Class<T> clazz, Long id) {
		T entity = null;
		log.info("Get entity by id " + id);
		entity = em.find(clazz, id);
		if (entity != null) {
			log.info("Found object for id " + id + ":  " + entity.toString());
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.ressys.basis.service.BasisRessysService#getAll()
	 */
	@SuppressWarnings("unchecked")
	protected List<T> getAll(String namedQueryName) {

		Query selectAllQuery = em.createNamedQuery(namedQueryName);
		List<T> resultList = (List<T>) selectAllQuery.getResultList();
		if (resultList != null) {
			log.info("there are " + resultList.size()
					+ " entries in the result list.");
		}
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.ressys.basis.service.BasisRessysService#update(java.lang
	 * .Object)
	 */
	public T update(T entity) {
		log.info("Update entiy" + entity.toString() + " ...");
		em.merge(entity);
		log.info("Entity updated.");

		return entity;
	}

	/**
	 * common delete operation for ressys services.
	 * 
	 * @param clazz
	 *            Class of the entity to delete.
	 * @param id
	 *            the id of the entity to delete.
	 * @return the instance of deleted object.
	 */
	protected T delete(Class<T> clazz, Long id) {
		log.info("Delete entity  with id " + id + "...");
		T dbEntity = em.find(clazz, id);
		em.remove(dbEntity);
		log.info("entity " + dbEntity.toString() + " was deleted.");

		return dbEntity;

	}

	/**
	 * returns the current instance of Entity managers.
	 * 
	 * @return the current instance of Entity managers.
	 */
	protected EntityManager getEntityManager() {
		return em;
	}

}
