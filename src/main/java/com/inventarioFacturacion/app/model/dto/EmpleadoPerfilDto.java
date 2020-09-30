/*
 * Fecha: 09-22-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.inventarioFacturacion.app.model.entity.PerfilEmpleado;
import com.inventarioFacturacion.app.services.EmpleadoPerfilService;


// TODO: Auto-generated Javadoc
/**
 * The Class EmpleadoPerfilDto.
 */
public class EmpleadoPerfilDto implements Serializable {
    @Autowired
	private EmpleadoPerfilService empleado;
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private long id;

	/** The nombre. */
	@Pattern(regexp = "(\\s?)+(([A-Z]?)[a-zA-Z])+((\\s?)+([A-Z]?)[a-zA-Z]+(\\s?)+)?", message = "* Complete el campo con un Nombre valido")
	private String nombre;

	/** The apellido. */
	@Pattern(regexp = "(\\s?)+(([A-Z]?)[a-zA-Z])+((\\s?)+([A-Z]?)[a-zA-Z]+(\\s?)+)?", message = "* Complete el campo con un Aplellido valido")
	// @Size(min = 3, max = 32, message = "* El campo debe contener entre 3 y 32
	// caracteres de longitud")
	private String apellido;

	/** The direccion. */
	@Size(min = 5, max = 32, message = "* Ingrese una dirección valida")
	private String direccion;

	/** The profesion. */
	@Size(min = 5, max = 80, message = "*Ingrese una Profesión valida")
	private String profesion;

	/** The contacto. */
	@Size(min = 4, max = 20, message = "* Ingrese un telefono ó email")
	private String contacto;

	/** The cargo. */
	@NotEmpty(message = "* El campo no puede estar vacio")
	private String cargo;

	/** The detalle. */
	private String detalle;

	@NotEmpty(message = "* Seleccione al menos un permiso ")
	private List<String> permisos;
	
	/** The empresa. */
	@NotNull(message = "* El campo no puede estar vacio")
	private Long empresa;

	/** The edad. */
	@NotNull(message = "* El campo no puede estar vacio, debe ser mayor de 14 años")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date edad;


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


	public Long getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Date getEdad() {
		return edad;
	}

	public List<String> getPermisos() {
		return permisos;
	}


	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
	}


	/**
	 * Sets the edad.
	 *
	 * @param edad the new edad
	 */
	public void setEdad(Date edad) {
		try {
			LocalDate actual = LocalDate.now();
			long edadValida = ChronoUnit.YEARS.between(edad.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), actual);
			if(edadValida>15) {
				this.edad = edad;
			}
		} catch (Exception e) {
			//pass
		}
		
		
	}

	@Override
	public String toString() {
		return "EmpleadoPerfilDto [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion="
				+ direccion + ", profesion=" + profesion + ", contacto=" + contacto + ", cargo=" + cargo + ", detalle="
				+ detalle + ", permisos=" + permisos + ", empresa=" + empresa + ", edad=" + edad + "]";
	}

}
