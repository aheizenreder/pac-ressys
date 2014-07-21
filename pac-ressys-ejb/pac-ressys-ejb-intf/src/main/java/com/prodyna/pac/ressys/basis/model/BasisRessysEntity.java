/**
 * 
 */
package com.prodyna.pac.ressys.basis.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

/**
 * This interface represents common interface for all entities in this project.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BasisRessysEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1351246154068666697L;

	/**
	 * Key field for all entities.
	 */
	private Long id;

	public BasisRessysEntity() {

	}
	
	public BasisRessysEntity(Long id){
		this.id = id;
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
