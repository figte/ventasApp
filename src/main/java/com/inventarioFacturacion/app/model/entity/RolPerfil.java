/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
// TODO: Auto-generated Javadoc


/**
 * The Class RolPerfil.
 */
@Entity
@Table(name = "roles_perfiles")
public class RolPerfil implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -388819311776465662L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	/** The perfil empleado. */
	@ManyToOne(fetch = FetchType.LAZY)
	private PerfilEmpleado perfilEmpleado;

	/** The rol. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Rol rol;

	/** The create at. */
	@Column(name = "create_at",  updatable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date createAt;

	/** The update at. */
	//@NotNull
	@Column(name = "update_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date updateAt;

	/**
	 * Instantiates a new rol perfil.
	 */
	public RolPerfil() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PerfilEmpleado getPerfilEmpleado() {
		return perfilEmpleado;
	}

	public void setPerfilEmpleado(PerfilEmpleado perfilEmpleado) {
		this.perfilEmpleado = perfilEmpleado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}


	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	

	@PrePersist
	public void setFechas() {
		this.createAt = new Date();
		this.updateAt = new Date();
		
	}

	@PreUpdate
	public void setUdtate() {
		this.updateAt = new Date();
	
	}

}
