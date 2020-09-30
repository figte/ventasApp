/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Rol.
 */
@SuppressAjWarnings
@Entity
@Table(name = "puestos")
public class Puesto implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5865156901695648061L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	/** The nombre. */
	// @NotEmpty(message = "* El campo Nombre no puede estar vacio")
	@Size(min = 6, max = 32, message = "* El nombre debe contener entre 6 y 32 caracteres de longitud")
	@Column(name = "nombre", nullable = false )
	private String nombre;

	/** The detalle. */
	@Column(name = "detalle")
	@Size(min = 6, max = 255, message = "* La descripción debe contener entre 6 y 255 caracteres de longitud")
	private String detalle;

	/** The create at. */
	@Column(name = "create_at",  updatable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date createAt;

	/** The update at. */
	@Column(name = "update_at", nullable = false )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date updateAt;
	

	public Puesto() {
	}

	public Puesto(@NotEmpty String nombre, @NotEmpty String detalle) {
		super();
		this.nombre = nombre;
		this.detalle = detalle;
	}

	public Puesto(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
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

	/*
	 * Creación de las fechas por medio del back end
	 * 
	 */
	
	/**
	 * Sets the Pre persist
	 */
	@PrePersist
	public void setFechas() {
		this.createAt = new Date();
		this.updateAt = new Date();
		this.nombre = this.nombre.toUpperCase();
		this.detalle = this.detalle.toUpperCase();
	}

	/**
	 * Sets the pre udtate.
	 */
	@PreUpdate
	public void setUdtate() {
		this.updateAt = new Date();
		this.nombre = this.nombre.toUpperCase();
		this.detalle = this.detalle.toUpperCase();
	}

}
