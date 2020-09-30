package com.inventarioFacturacion.app.model.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.annotation.SuppressAjWarnings;

@SuppressAjWarnings
@Entity
@Table(name = "productos")
public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -495546993462361031L;
	
	/** The codigo */
	@Id
	@Column(name = "codigo", unique = true)
	private String codigo;

	/** The nombre. */
	@Column(name = "nombre", nullable = false, unique = true )
	private String nombre;

	/** The proveedor. */
	@ManyToOne(fetch = FetchType.EAGER)
	private Proveedor proveedor;
	
	/** The existencias */
	@Column(name = "existencias")
	private Integer existencias;
		
	/** The existencias */
	@Column(name = "stock")
	private String stock;
	
	/** The unidad de medida */
	@Column(name = "unidad_medida")
	private String unidadDeMedida;
	
	/** The esdtado */
	@Column(name = "estado", nullable = false)
	private Integer estado;
	
	/**precio promedio sin impuestos*/
	@Column(name = "precio_venta")
	private Double precio;
	
	/** The impuestos */
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Impuesto> impuestos;
	
	/** The impuestos */
	@ManyToOne(fetch = FetchType.EAGER)
	private Categoria categoria;
	/** The productos. */

	private Boolean impuesto_CESC;
		
	/**constructor**/
	public Producto() {	
		impuestos = new ArrayList<Impuesto>();
		//entradas = new ArrayList<Entradas>();
	}
 
	/**
	 * @param impuesto_CESC the impuesto_CESC to set
	 */
	public void setImpuesto_CESC(Boolean impuesto_CESC) {
		this.impuesto_CESC = impuesto_CESC;
	}
	/**
	 * @return the impuesto_CESC
	 */
	public Boolean getImpuesto_CESC() {
		return impuesto_CESC;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public Integer getExistencias() {
		return existencias;
	}

	public void setExistencias(Integer existencias) {
		this.existencias = existencias;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public List<Impuesto> getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(List<Impuesto> impuestos) {
		this.impuestos = impuestos;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	/* public List<Entradas> getEntradas() {
	 	return entradas;
	 }

	 public void setEntradas(List<Entradas> entradas) {
	 	this.entradas = entradas;
	 }*/

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}	
	
	
}
