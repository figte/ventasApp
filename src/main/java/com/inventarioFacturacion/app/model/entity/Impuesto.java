package com.inventarioFacturacion.app.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.aspectj.lang.annotation.SuppressAjWarnings;

@SuppressAjWarnings
@Entity
@Table(name = "impuestos")
public class Impuesto {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	
	/** The nombre.*/
	@NotEmpty
	@Column(name = "nombre")
	private String nombre;
    
	/** The impuesto*/
	@NotEmpty
	@Column(name = "impuesto")
	private double impuesto;

	/** The impuestos */
	@ManyToMany(mappedBy ="impuestos" ,fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	private List<Producto> producto;
	
	public Impuesto() {
		producto = new ArrayList<Producto>();
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

	public double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}
	
}
