/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Empleado
 */
@SuppressAjWarnings
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1731135301547956571L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	/** The nombre. */
	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	/** The apellido. */
	@NotEmpty
	@Column(name = "apellido")
	private String apellido;

	/** The direccion. */
	@NotEmpty
	@Column(name = "direccion")
	private String direccion;

	/** The profesion. */
	@NotEmpty
	@Column(name = "profesion")
	private String profesion;

	/** The contacto. */
	@NotEmpty
	@Column(name = "contacto")
	private String contacto;

	/** The cargo. */
	@NotEmpty
	@Column(name = "cargo")
	private String cargo;


	/** The detalle. */
	@Column(name = "detalle")
	private String detalle;
	
	/** The edad. */
	@Column(name = "edad")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date edad;
	
	/** The create at. */
	@Column(name = "create_at", updatable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date createAt;

	/** The update at. */
	//@NotNull
	@Column(name = "update_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date updateAt;
	
	/** The sucursal. */
	//@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Sucursal sucursal;
	
	/** The perfil. */
	//@NotNull
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "empleados", cascade = CascadeType.ALL,  orphanRemoval = true)
	private PerfilEmpleado perfil;
    
	
	/**
	 * Instantiates a new empleado.
	 *
	 * @param nombre the nombre
	 * @param apellido the apellido
	 * @param direccion the direccion
	 * @param profesion the profesion
	 * @param contacto the contacto
	 * @param cargo the cargo
	 * @param sucursal the sucursal
	 * @param perfil the perfil
	 * @param edad the edad
	 */
	public Empleado(@NotEmpty String nombre, @NotEmpty String apellido, @NotEmpty String direccion,
			@NotEmpty String profesion, @NotEmpty String contacto, @NotEmpty String cargo, Sucursal sucursal,
			PerfilEmpleado perfil, Date edad) {
		//this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.profesion = profesion;
		this.contacto = contacto;
		this.cargo = cargo;
		this.sucursal = sucursal;
		this.perfil = perfil;
		this.edad = edad;
	}

	/**
	 * Instantiates a new empleado.
	 *
	 * @param nombre the nombre
	 * @param apellido the apellido
	 * @param perfil the perfil
	 */
	public Empleado(String nombre, String apellido, PerfilEmpleado perfil) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.perfil = perfil;
	}

	/**
	 * Instantiates a new empleado.
	 */
	// Constructores
	public Empleado() {
	}


	public PerfilEmpleado getPerfil() {
		return perfil;
	}


	public void setPerfilEmpleado(PerfilEmpleado perfilEmpleado) {
		this.perfil= perfilEmpleado;
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


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getContacto() {
		return contacto;
	}


	public void setContacto(String contacto) {
		this.contacto = contacto;
	}


	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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


	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Empleados [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", profesion=" + profesion + ", contacto=" + contacto + ", cargo=" + cargo + ", detalle=" + detalle
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + ", sucursal=" + sucursal + ", perfil=" + perfil
				+ "]";
	}


	public Date getEdad() {
		return edad;
	}

	public void setEdad(Date edad) {
		this.edad = edad;
	}

	public void setPerfil(PerfilEmpleado perfil) {
		this.perfil = perfil;
	}
	
	/**
	 * Sets the fechas.
	 */
	@PrePersist
	public void setFechas() {
		this.createAt = new Date();
		this.updateAt = new Date();
		this.nombre = this.nombre.toUpperCase();
		this.apellido = this.apellido.toUpperCase();
	}

	/**
	 * Sets the udtate.
	 */
	@PreUpdate
	public void setUdtate() {
		this.createAt = new Date();
		this.updateAt = new Date();
		this.nombre = this.nombre.toUpperCase();
		this.apellido = this.apellido.toUpperCase();
	}
	
}
