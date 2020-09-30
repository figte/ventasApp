/*
 * Fecha: 11-05-2019
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.aspectj.lang.annotation.SuppressAjWarnings;
// TODO: Auto-generated Javadoc
/**
 * The Class Categoria.
 */
@SuppressAjWarnings
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6120537379347599899L;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	/** The nombre. */
	@NotEmpty
	@Column(name = "nombre", nullable = false, unique = true )
	private String nombre;

	/** The detalle. */
	@Column(name = "detalle")
	private String detalle;

	
	/** The estado. */
	@NotNull
	@Column(name = "estado")
	private int estado;
	
	// /** The productos. */
	// @OneToMany(mappedBy ="categoria" ,fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	// private List<Producto> producto;
    
	public Categoria() {
		 //producto = new ArrayList<Producto>();
	}
	// public List<Producto> getProducto() {
	// 	return producto;
	// }
	// public void setProducto(List<Producto> producto) {
	// 	this.producto = producto;
	// }
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
		this.nombre = nombre.toUpperCase();
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
