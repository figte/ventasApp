/*
 * Fecha: 11-06-2019
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

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Proveedor.
 */
@SuppressAjWarnings
@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4092185841017367963L;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	/** The nombre. */
	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	/** The apcontacto */
	@NotEmpty
	@Column(name = "contacto")
	private String contacto;
	
	/** The Detalle */
	@Column(name = "detalle")
	private String detalle;
	
	/** The estado. */
	@NotEmpty
	@Column(name = "estado")
	private int estado;
 	
	
	/** The productos. */
	// @OneToMany(mappedBy ="proveedor" ,fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	// private List<Producto> producto;
    

	public Proveedor() {
		// producto = new ArrayList<Producto>();
		
	}

	// public List<Producto> getProducto() {
	// 	return producto;
	// }

	// public void setProducto(List<Producto> producto) {
	// 	this.producto = producto;
	// }

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

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	
}
