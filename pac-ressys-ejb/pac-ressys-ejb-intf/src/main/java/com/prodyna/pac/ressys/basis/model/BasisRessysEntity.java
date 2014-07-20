/**
 * 
 */
package com.prodyna.pac.ressys.basis.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * This interface represents common interface for all entities in this project.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
public class BasisRessysEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1351246154068666697L;

	private Long id;

	public BasisRessysEntity() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
