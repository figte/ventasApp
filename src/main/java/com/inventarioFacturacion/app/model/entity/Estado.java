/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Estado.
 */
@Entity
@Table(name = "estados")
public class Estado implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7086427805323044690L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	/** The nombre. */
	@NotEmpty
	@Column(name = "nombre", nullable = false )
	private String nombre;

	/** The detalle. */
	@NotEmpty
	@Column(name = "detalle")
	private String detalle;

	/** The create at. */
	@Column(name = "create_at",  updatable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date createAt;

	/** The update at. */
	@NotNull
	@Column(name = "update_at", nullable = false )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date updateAt;

	/** The perfil. */
	@OneToMany(mappedBy = "estado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PerfilEmpleado> perfil;
	
	public Estado() {
		perfil = new ArrayList<PerfilEmpleado>();
	}

	public Estado(long id) {
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


	public List<PerfilEmpleado> getPerfil() {
		return perfil;
	}


	public void setPerfil(List<PerfilEmpleado> perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", nombre=" + nombre + ", detalle=" + detalle + ", createAt=" + createAt
				+ ", updateAt=" + updateAt + ", perfilEmpleado=" + perfil + "]";
	}

}
