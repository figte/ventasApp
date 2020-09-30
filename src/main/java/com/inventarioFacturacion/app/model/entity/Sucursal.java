/**
 * Jaime Ramirez
 * 21/8/2019
 * Entidad sucursal
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Sucursal.
 */
@Entity
@Table(name="sucursales")
public class Sucursal implements Serializable {
	
	/** The Constant serialVersionUID. */
	//Serialización
	private static final long serialVersionUID = -4097745174103620547L;
	
	/** The id. */
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	/** The nombre. */
	@Column(name = "nombre",nullable = false, unique = true)
	@NotEmpty(message = "* Campo obligatorio")
	private String nombre;
	
	/** The telefono. */
	@Column(name = "telefono")
	@NotEmpty(message = "* Campo obligatorio")
	private String telefono;
	
	/** The email. */
	@Column(name = "email")
	private String email;
	
	/** The direccion. */
	@Column(name = "direccion")
	@NotEmpty(message = "* Campo obligatorio")
	private String direccion;
	
	/** The detalle. */
	@Column(name = "detalle")
	private String detalle;

	/** The create at. */
	@Column(name = "create_at",  updatable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date createAt;
	
	/** The update at. */
	@Column(name = "update_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date updateAt;
	
	/** The usuarios. */
	@OneToMany(mappedBy ="sucursal" ,fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	//@JoinColumn(name = "id_sucursal")
	private List<Empleado> usuarios;
    
	/**
	 * Instantiates a new sucursal.
	 */
	//Constructores
	public Sucursal() {
		 usuarios = new ArrayList<Empleado>();
	}


	public Sucursal(Long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
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


	public List<Empleado> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Empleado> usuarios) {
		this.usuarios = usuarios;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
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


	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email
				+ ", direccion=" + direccion + ", detalle=" + detalle + ", createAt=" + createAt + ", updateAt="
				+ updateAt + ", usuarios=" + usuarios + "]";
	}
    
}
