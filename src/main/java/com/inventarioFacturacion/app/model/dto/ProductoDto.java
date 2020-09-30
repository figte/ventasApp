package com.inventarioFacturacion.app.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.stereotype.Component;

/**
 * ProductoDto
 */
@Component
public class ProductoDto {
   
	@NotEmpty(message = "*campo requerido")
	private String codigo;

	/** The nombre. */
	@NotEmpty(message = "*campo requerido")
	private String nombre;

	@NotNull(message = "*campo requerido")
	/** The proveedor. */
	private Long idProveedor;
	
	/** The existencias */
	@NotNull(message = "*campo requerido")
	@Min(value = 1,message = "*tiene que colocar como minimo 1 producto en existencias")
	private Integer existencias;
	
	
	/** The existencias */
	
	@NotEmpty(message = "*campo requerido")
	private String stock;
	
	/** The unidad de medida */

	
	private String unidadDeMedida;
	
	/** The esdtado */
	
	@NotNull(message = "*campo requerido")
	@Min(value = 1,message = "*tiene que colocar como minimo el valor 1")
	private Integer estado;
	
	/**precio promedio sin impuestos*/
	
	@NotNull(message = "*campo requerido")
	@DecimalMin("0.01")
	@Digits(integer=100, fraction=2, message = "* Solo usar 2 decimales")
	private Double precio;
	

	@NotNull(message = "*campo requerido")
	private Long idCategoria;
	/** The productos. */
	
	@NotNull(message = "*campo requerido")
	private Integer impuesto_CESC;

	/**
	 * @param impuesto_CESC the impuesto_CESC to set
	 */
	public void setImpuesto_CESC(Integer impuesto_CESC) {
		this.impuesto_CESC = impuesto_CESC;
	}
	/**
	 * @return the impuesto_CESC
	 */
	public Integer getImpuesto_CESC() {
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

	

	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}	

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	/**
	 * @return the idCategoria
	 */
	public Long getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	/**
	 * @return the idProveedor
	 */
	public Long getIdProveedor() {
		return idProveedor;
	}
}